package com.chengshi.train.authentication;

import com.chengshi.train.util.ResponseBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("trainAuthenticationFailureHandler")
@Slf4j
public class TrainAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.info("登陆失败！");

        httpServletResponse.setHeader("Content-Type", "application/json;charset=utf-8");

        ResponseBean responseBean=new ResponseBean(1003,"登陆验证失败");
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(responseBean));
        httpServletResponse.getWriter().flush();
    }
}
