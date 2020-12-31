package org.example.browser.config;

import org.example.core.AbstractWebSecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author tjstj
 * @description TODO
 * @date 2020/11/21 21:51
 */
@Configuration
@EnableWebSecurity
public class BrowserSecurityConfig extends AbstractWebSecurityConfig {

    public PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable()
                .formLogin()
                .and().headers().frameOptions().sameOrigin();
    }
}
