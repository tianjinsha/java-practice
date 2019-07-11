package com.cehngshi.train.filter;

import com.cehngshi.train.domain.Audience;
import com.cehngshi.train.exception.TrainSecurityException;
import com.cehngshi.train.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtFilter extends GenericFilterBean {

    @Autowired
    private Audience audience;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest request= (HttpServletRequest) servletRequest;
        final HttpServletResponse response= (HttpServletResponse) servletResponse;
        final String authHeader= request.getHeader("authorization");

        if ("OPTIONS".equals(request.getMethod())){
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                log.warn("没有权限");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                filterChain.doFilter(servletRequest,servletResponse);
//                throw new TrainSecurityException("没有权限");
                return;
            }
            final String token = authHeader.substring(7);
            try {
                if(audience == null){
                    BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
                    audience = (Audience) factory.getBean("audience");
                }
                final Claims claims = JwtUtil.parseJwt(token,audience.getBase64Secret());
                if(claims == null){
//                    throw new LoginException(ResultEnum.LOGIN_ERROR);
                }
                request.setAttribute("CLAIMS", claims);
            } catch (final Exception e) {
//                throw new LoginException(ResultEnum.LOGIN_ERROR);
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
