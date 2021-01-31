package org.security.app.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.core.properties.ProjectSecurityProperties;
import org.security.app.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
    private ProjectSecurityProperties properties;

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
