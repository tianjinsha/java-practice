package com.chengshi.train.config;

import com.chengshi.train.filter.JwtAuthenticationTokenFilter;
import com.chengshi.train.authentication.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Autowired
    private UserDetailsService userDetailsService;

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
        auth.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
//                .loginPage("/authentication/require")
                .loginPage("/login.html")
                .loginProcessingUrl("/authentication/form")
                .successHandler(trainAuthenticationSuccessHandler)
                .failureHandler(trainAuthenticationFailureHandler)
                .and()
                .httpBasic()
                .authenticationEntryPoint(trainAuthenticationEntryPoint)
                .and()
                    .logout()
                    .logoutSuccessHandler(trainLogoutSuccessHandler)
                    .deleteCookies("JSESSIONID")
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/resources/**", "/login","/authentication/require","/authentication/form","/login.html").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();


        //记住我
        http.rememberMe().rememberMeParameter("remember-me")
                .userDetailsService(userDetailsService).tokenValiditySeconds(300);

        //jwt过滤器
        http.exceptionHandling()
                .accessDeniedHandler(trainAccessDeniedHandler)
        .and().addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

    }

    // 装载BCrypt密码编码器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
