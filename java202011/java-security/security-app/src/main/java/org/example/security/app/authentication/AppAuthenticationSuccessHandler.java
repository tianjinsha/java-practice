package org.example.security.app.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.core.constant.CommonErrorCodeBase;
import org.example.core.protocol.CommonResult;
import org.example.security.app.protocol.JwtTokenBean;
import org.example.security.app.util.JwtTokenUtil;
import org.example.security.core.properties.ProjectSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author tjstj
 * @description TODO
 * @date 2021/1/31 22:09
 */
@Slf4j
@Component("appAuthenticationSuccessHandler")
public class AppAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProjectSecurityProperties securityProperties;

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        log.debug("登陆成功!");
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwtToken = jwtTokenUtil.generateToken(userDetails);
        jwtTokenUtil.set(jwtToken,userDetails.getUsername(),securityProperties.getJwt().getExpiration()+600);

        CommonResult result = new CommonResult();
        JwtTokenBean bean = new JwtTokenBean(CommonErrorCodeBase.SUCCESS,"登录成功!",jwtToken);
        result.setCommand(securityProperties.getSignInUrl());
        result.setParam(bean);
        response.getWriter().flush();
        log.debug("Bearer "+jwtToken);
    }
}
