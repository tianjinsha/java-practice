package com.chengshi.train.authentication;

import com.chengshi.train.properties.LoginResponseType;
import com.chengshi.train.properties.TrainSecurityProperties;
import com.chengshi.train.util.ResponseBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 负责启动未经过身份验证的用户的身份验证过程(当他们试图访问受保护的资源)
 * 错误-401
 */
@Slf4j
@Component("trainAuthenticationEntryPoint")
public class TrainAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    private TrainSecurityProperties trainSecurityProperties;

    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.warn("没有登陆");
        if (LoginResponseType.JSON.name().equals(trainSecurityProperties.getLoginType())) {
            httpServletResponse.setHeader("Content-Type", "application/json;charset=utf-8");
            ResponseBean responseBean=new ResponseBean(1001,"还未登录，无法访问该资源");
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(responseBean));
            httpServletResponse.getWriter().flush();
        }else{
            log.info("调整");
        }

    }
}
