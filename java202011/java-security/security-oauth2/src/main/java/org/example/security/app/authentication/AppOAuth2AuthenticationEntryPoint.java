package org.example.security.app.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.core.constant.CommonErrorCodeBase;
import org.example.core.constant.ResultEnum;
import org.example.core.protocol.CommonBean;
import org.example.core.protocol.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author tjstj
 * @description TODO
 * @date 2021/2/15 20:24
 */
@Slf4j
@Component("appOAuth2AuthenticationEntryPoint")
public class AppOAuth2AuthenticationEntryPoint extends OAuth2AuthenticationEntryPoint {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        CommonResult<CommonBean> result = new CommonResult<>();
        result.setResult(ResultEnum.Fail.getResult());
        result.setMessage(ResultEnum.Fail.getMessage());
        result.setPath(request.getRequestURI());
        CommonBean bean = new CommonBean(CommonErrorCodeBase.UNAUTHORIZED, "未认证，无法访问该资源!");
        result.setParam(bean);
        response.getWriter().write(objectMapper.writeValueAsString(result));
        response.getWriter().flush();
    }
}
