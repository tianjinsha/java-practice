package com.cheng.train.config;

import com.cheng.train.authentication.TrainAuthenctiationFailureHandler;
import com.cheng.train.authentication.TrainAuthenticationSuccessHandler;
import com.cheng.train.util.properties.SecurityProperties;
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
public class TrainSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private UserDetailsService trainUserDetailsService;

    @Autowired
    private TrainAuthenticationSuccessHandler trainAuthenticationSuccessHandler;
    @Autowired
    private TrainAuthenctiationFailureHandler trainAuthenctiationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(securityProperties.getLoginPage())
                .loginProcessingUrl(securityProperties.getLoginProcessUrl())
                .successHandler(trainAuthenticationSuccessHandler)
                .failureHandler(trainAuthenctiationFailureHandler)
                .and().authorizeRequests()
                .antMatchers(
                        securityProperties.getSignUpUrl(),
                        securityProperties.getLoginPage(),
                        "/login").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
        super.configure(http);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //添加自定义的userDetailsService认证
        auth.userDetailsService(this.trainUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
