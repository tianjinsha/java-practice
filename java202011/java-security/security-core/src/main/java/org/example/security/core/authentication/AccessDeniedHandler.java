package org.example.security.core.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.core.constant.CommonErrorCodeBase;
import org.example.core.constant.ResultEnum;
import org.example.security.core.properties.ResponseType;
import org.example.security.core.properties.ProjectSecurityProperties;
import org.example.core.protocol.CommonBean;
import org.example.core.protocol.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: tjshan
 * @date: 2021/1/17 3:49 下午
 * FileName: AccessDeniedHandler
 * Description: 错误-403
 */
@Slf4j
@Component
public class AccessDeniedHandler extends AccessDeniedHandlerImpl {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProjectSecurityProperties securityProperties;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        if (ResponseType.JSON.name().equals(securityProperties.getResponseType())) {
            response.setHeader("Content-Type", "application/json;charset=utf-8");
            CommonResult<CommonBean> result = new CommonResult<>();
            result.setResult(ResultEnum.Fail.getResult());
            result.setMessage(ResultEnum.Fail.getMessage());
            result.setPath(request.getRequestURI());
            CommonBean bean = new CommonBean(CommonErrorCodeBase.FORBIDDEN, "权限不足,无法访问该资源!");
            result.setParam(bean);
            response.getWriter().write(objectMapper.writeValueAsString(result));
            response.getWriter().flush();
        }else{
            super.handle(request, response, accessDeniedException);
        }
    }
}
