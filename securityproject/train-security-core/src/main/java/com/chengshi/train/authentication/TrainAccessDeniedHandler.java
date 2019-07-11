package com.chengshi.train.authentication;

import com.chengshi.train.properties.LoginResponseType;
import com.chengshi.train.properties.TrainSecurityProperties;
import com.chengshi.train.util.ResponseBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 如果用户已经通过身份验证，试图访问受保护的(该用户没有权限的)资源
 */
@Slf4j
@Component("trainAccessDeniedHandler")
public class TrainAccessDeniedHandler extends AccessDeniedHandlerImpl {

    @Autowired
    private TrainSecurityProperties trainSecurityProperties;
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        log.warn("权限不足");
        if (LoginResponseType.JSON.name().equals(trainSecurityProperties.getLoginType())) {
            response.setHeader("Content-Type", "application/json;charset=utf-8");
            ResponseBean responseBean = new ResponseBean(1003, "您的权限不足，无法访问该资源");
            response.getWriter().write(objectMapper.writeValueAsString(responseBean));
            response.getWriter().flush();
        }else {
            super.handle(request,response,e);
        }

    }
}
