package org.example.security.app.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.core.constant.ResultEnum;
import org.example.core.protocol.CommonBean;
import org.example.core.protocol.CommonResult;
import org.example.core.util.BeanUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * @author tjstj
 * @description TODO
 * @date 2021/2/15 20:36
 */
@Slf4j
public class AppOauth2ExceptionSerializer extends StdSerializer<AppOauth2Exception> {
    protected AppOauth2ExceptionSerializer() {
        super(AppOauth2Exception.class);
    }

    @Override
    public void serialize(AppOauth2Exception e, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        jsonGenerator.writeStartObject();

        CommonResult<CommonBean> result = new CommonResult<>();
        result.setResult(ResultEnum.Fail.getResult());
        result.setMessage(ResultEnum.Fail.getMessage());
        result.setPath(request.getRequestURI());
        result.setTimestamp(System.currentTimeMillis());
        CommonBean bean = new CommonBean();
        bean.setCode(StringUtils.leftPad(String.valueOf(e.getHttpErrorCode()),4,"0"));

        String message = e.getMessage();
        if (message != null) {
            message = HtmlUtils.htmlEscape(message);
        }
        bean.setMessage(message);
        bean.setError(e.getOAuth2ErrorCode());
        result.setParam(bean);

        Map<String, Object> beanMap = BeanUtils.beanToMap(result);

        for (Map.Entry<String, Object> entry : beanMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if(value instanceof CommonBean){
                jsonGenerator.writeObjectField(key,value);
            }else {
                jsonGenerator.writeStringField(key, value.toString());
            }
        }

//        if (e.getAdditionalInformation()!=null) {
//            for (Map.Entry<String, String> entry : e.getAdditionalInformation().entrySet()) {
//                String key = entry.getKey();
//                String add = entry.getValue();
//                jsonGenerator.writeStringField(key, add);
//            }
//        }

        jsonGenerator.writeEndObject();
    }
}
