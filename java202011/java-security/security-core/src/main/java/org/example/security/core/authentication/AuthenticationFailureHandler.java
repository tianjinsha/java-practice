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
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: tjshan
 * @date: 2021/1/17 2:09 下午
 * FileName: ProjectAuthenticationFailureHandler
 * Description:
 */
@Slf4j
@Component
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProjectSecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        if (ResponseType.JSON.name().equals(securityProperties.getResponseType())) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setHeader("Content-Type", "application/json;charset=utf-8");
            CommonResult<CommonBean> result = new CommonResult<>();
            result.setResult(ResultEnum.Fail.getResult());
            result.setMessage(ResultEnum.Fail.getMessage());
            CommonBean bean = new CommonBean(CommonErrorCodeBase.UNAUTHORIZED, exception.getMessage());
            result.setPath(securityProperties.getLoginProcessUrl());
            result.setParam(bean);
            response.getWriter().write(objectMapper.writeValueAsString(result));
            response.getWriter().flush();
        }else{
            setDefaultFailureUrl(securityProperties.getSignFailUrl());
            super.onAuthenticationFailure(request, response, exception);
        }
    }
}