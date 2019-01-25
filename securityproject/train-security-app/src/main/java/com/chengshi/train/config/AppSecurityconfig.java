package com.chengshi.train.config;


import com.chengshi.train.config.AbstractChannelSecurityConfig;
import com.chengshi.train.properties.TrainSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityconfig extends AbstractChannelSecurityConfig {

    @Autowired
    private TrainSecurityProperties trainSecurityProperties;

    @Autowired
    private UserDetailsService trainUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        applyPasswordAuthenticationConfig(http);
        http.
                authorizeRequests()
                .antMatchers(trainSecurityProperties.getLoginPage(),
                        trainSecurityProperties.getLoginProcessUrl(),
                        trainSecurityProperties.getSignUpUrl(),
                        "/logout","/js/**", "/images/**", "/css/**", "/resources/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }
}
