package org.example.security.app.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.security.app.util.JwtTokenUtil;
import org.example.security.core.properties.ProjectSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author tjstj
 * @description TODO
 * @date 2021/1/31 22:11
 */
@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private ProjectSecurityProperties securityProperties;

    @Qualifier("accountUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader(securityProperties.getJwt().getHeader());
        if(StringUtils.isNotEmpty(authHeader) && authHeader.startsWith(securityProperties.getJwt().getTokenHead())){
            String authToken = authHeader.substring(securityProperties.getJwt().getTokenHead().length());
            String username = jwtTokenUtil.getUsernameFromToken(authToken);
            if(username !=null && SecurityContextHolder.getContext().getAuthentication() ==null){
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if(jwtTokenUtil.validateToken(authToken)){
                    if(jwtTokenUtil.validateToken(authToken,userDetails)){
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }else{
                        jwtTokenUtil.delete(authToken);
                    }
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
