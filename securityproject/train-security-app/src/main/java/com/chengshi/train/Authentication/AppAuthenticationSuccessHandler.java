package com.chengshi.train.Authentication;

import com.chengshi.train.properties.TrainSecurityProperties;
import com.chengshi.train.util.JwtTokenUtil;
import com.chengshi.train.util.RCode;
import com.chengshi.train.util.ResponseBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component("appAuthenticationSuccessHandler")
public class AppAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TrainSecurityProperties properties;

    @Autowired
    JwtTokenUtil jwtTokenUtil;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        log.info("登陆成功！");
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwtToken = jwtTokenUtil.generateToken(userDetails);
        jwtTokenUtil.set(jwtToken,userDetails.getUsername(),properties.getJwt().getExpiration()+600);
        ResponseBean responseBean=new ResponseBean<String>(jwtToken, RCode.LOGINSUCCESS);
        response.getWriter().write(objectMapper.writeValueAsString(responseBean));
        response.getWriter().flush();
        log.info("Bearer "+jwtToken);
    }
}
