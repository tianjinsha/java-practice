package org.example.core.config;

import org.example.core.authentication.AccessDeniedHandler;
import org.example.core.authentication.AuthenticationFailureHandler;
import org.example.core.authentication.AuthenticationSuccessHandler;
import org.example.core.authentication.LogoutSuccessHandler;
import org.example.core.properties.ProjectSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author tjstj
 * @description TODO
 * @date 2020/11/21 22:35
 */
public class AbstractWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ProjectSecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
        http.formLogin()
                //登录页
                .loginPage(securityProperties.getLoginPage())
                //登录处理接口
                .loginProcessingUrl(securityProperties.getLoginProcessUrl())
                //登录成功处理器
                .successHandler(authenticationSuccessHandler)
                //登录失败处理器
                .failureHandler(authenticationFailureHandler)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .and()
                .logout()
                //退出路径
                .logoutUrl(securityProperties.getSignOutUrl())
                //登出成功处理器
                .logoutSuccessHandler(logoutSuccessHandler)
                //删除session
                .deleteCookies("JSESSIONID")
        ;
    }
}
