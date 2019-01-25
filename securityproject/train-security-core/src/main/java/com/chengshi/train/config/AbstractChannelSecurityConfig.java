package com.chengshi.train.config;


import com.chengshi.train.authentication.*;

import com.chengshi.train.properties.TrainSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TrainSecurityProperties trainSecurityProperties;

    @Autowired
    private UserDetailsService trainUserDetailsService;

    @Autowired
    private TrainAuthenticationSuccessHandler trainAuthenticationSuccessHandler;

    @Autowired
    private TrainAuthenticationFailureHandler trainAuthenticationFailureHandler;

    @Autowired
    private TrainLogoutSuccessHandler trainLogoutSuccessHandler;

    @Autowired
    //401
    private TrainAuthenticationEntryPoint trainAuthenticationEntryPoint;

    @Autowired
    //403
    private TrainAccessDeniedHandler trainAccessDeniedHandler;

    @Autowired
    private DataSource dataSource;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //添加自定义的userDetailsService认证
        auth.userDetailsService(this.trainUserDetailsService).passwordEncoder(passwordEncoder());
    }

    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(trainSecurityProperties.getLoginPage())
                .loginProcessingUrl(trainSecurityProperties.getLoginProcessUrl())
                .successHandler(trainAuthenticationSuccessHandler)
                .failureHandler(trainAuthenticationFailureHandler)

                .and()
                .exceptionHandling()
                .accessDeniedHandler(trainAccessDeniedHandler)

                .and()
                .httpBasic()
                .authenticationEntryPoint(trainAuthenticationEntryPoint)
                .and()
                .logout()
                .logoutSuccessHandler(trainLogoutSuccessHandler)
                .deleteCookies("JSESSIONID");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        tokenRepository.setCreateTableOnStartup(false);
        return tokenRepository;
    }
}
