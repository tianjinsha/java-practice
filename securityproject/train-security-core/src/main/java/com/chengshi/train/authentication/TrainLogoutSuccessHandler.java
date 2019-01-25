package com.chengshi.train.authentication;

import com.chengshi.train.properties.LoginResponseType;
import com.chengshi.train.properties.TrainSecurityProperties;
import com.chengshi.train.util.ResponseBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component("trainlogoutsuccesshandler")
public class TrainLogoutSuccessHandler  extends SimpleUrlLogoutSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TrainSecurityProperties trainSecurityProperties;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
       log.info("注销成功！");
        if (LoginResponseType.JSON.name().equals(trainSecurityProperties.getLoginType())) {
            response.setHeader("Content-Type", "application/json;charset=utf-8");
            ResponseBean responseBean = new ResponseBean(1000, "注销成功");
            response.getWriter().write(objectMapper.writeValueAsString(responseBean));
            response.getWriter().flush();
        }else{
            log.info("注销后跳转！");
            setDefaultTargetUrl(trainSecurityProperties.getSignUpUrl());
            super.onLogoutSuccess(request,response,authentication);
        }
    }
}
