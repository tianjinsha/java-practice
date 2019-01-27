package com.chengshi.train.authentication;


import com.chengshi.train.properties.LoginResponseType;
import com.chengshi.train.properties.TrainSecurityProperties;
import com.chengshi.train.util.ResponseBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("trainAuthenticationFailureHandler")
@Slf4j
public class TrainAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TrainSecurityProperties trainSecurityProperties;
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        log.info("登陆失败！");
        if (LoginResponseType.JSON.name().equals(trainSecurityProperties.getLoginType())) {
            log.info("json");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setHeader("Content-Type", "application/json;charset=utf-8");
            ResponseBean responseBean = new ResponseBean(1006, "登陆验证失败");
            response.getWriter().write(objectMapper.writeValueAsString(responseBean));
            response.getWriter().flush();
        }else{
            log.info("redirect");
//            setDefaultFailureUrl("/signIn.html");
            super.onAuthenticationFailure(request, response, e);
        }
    }
}
