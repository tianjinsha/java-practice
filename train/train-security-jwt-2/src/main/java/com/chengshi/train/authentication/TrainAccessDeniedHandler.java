package com.chengshi.train.authentication;

import com.chengshi.train.util.ResponseBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 如果用户已经通过身份验证，试图访问受保护的(该用户没有权限的)资源
 */
@Component("trainAccessDeniedHandler")
public class TrainAccessDeniedHandler implements AccessDeniedHandler {
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
        httpServletResponse.setHeader("Content-Type", "application/json;charset=utf-8");
        ResponseBean responseBean= new ResponseBean(1004,"您的权限不足，无法访问该资源");

        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(responseBean));
        httpServletResponse.getWriter().flush();

    }
}
