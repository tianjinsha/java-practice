package com.chengshi.train.authentication;


import com.chengshi.train.properties.LoginResponseType;
import com.chengshi.train.properties.TrainSecurityProperties;
import com.chengshi.train.util.ResponseBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component("trainAuthenticationSuccessHandler")
public class TrainAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private TrainSecurityProperties trainSecurityProperties;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        log.info("登陆成功！");
        if (LoginResponseType.JSON.equals(trainSecurityProperties.getLoginType())) {
            response.setHeader("Content-Type", "application/json;charset=utf-8");
            ResponseBean responseBean = new ResponseBean(1005, "登陆验证成功");
            response.getWriter().write(objectMapper.writeValueAsString(responseBean));
            response.getWriter().flush();
        }else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
