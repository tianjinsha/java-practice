package org.example.security.core.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.core.constant.CommonErrorCodeBase;
import org.example.core.constant.ResultEnum;
import org.example.security.core.properties.ProjectSecurityProperties;
import org.example.security.core.properties.ResponseType;
import org.example.core.protocol.CommonBean;
import org.example.core.protocol.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author tjstj
 * @description TODO
 * @date 2021/1/24 12:58
 */
@Component("authenticationEntryPointHandler")
public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    @Autowired
    private ProjectSecurityProperties securityProperties;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 重定向策略
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {

        if (ResponseType.JSON.name().equals(securityProperties.getResponseType())) {
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
        }else{
            redirectStrategy.sendRedirect(request, response, securityProperties.getSignInUrl());
        }
    }
}
