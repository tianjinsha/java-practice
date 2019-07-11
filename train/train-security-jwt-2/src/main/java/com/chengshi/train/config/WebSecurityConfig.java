package com.chengshi.train.config;

import com.chengshi.train.authentication.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService trainUserDetailsService;

    @Autowired
    private TrainAuthenticationSuccessHandler trainAuthenticationSuccessHandler;

    @Autowired
    private TrainAuthenticationFailureHandler trainAuthenticationFailureHandler;

    @Autowired
    private TrainLogoutSuccessHandler trainLogoutSuccessHandler;

    @Autowired
    private TrainAuthenticationEntryPoint trainAuthenticationEntryPoint;

    @Autowired
    private TrainAccessDeniedHandler trainAccessDeniedHandler;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //添加自定义的userDetailsService认证
        auth.userDetailsService(this.trainUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .successHandler(trainAuthenticationSuccessHandler)
                .failureHandler(trainAuthenticationFailureHandler)
                .and()
                .httpBasic()
                .authenticationEntryPoint(trainAuthenticationEntryPoint)
                .and()
                .logout()
                .logoutSuccessHandler(trainLogoutSuccessHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/resources/**", "/login","/login.html").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }

    // 装载BCrypt密码编码器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
