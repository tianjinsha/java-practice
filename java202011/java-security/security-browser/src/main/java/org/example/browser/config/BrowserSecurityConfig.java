package org.example.browser.config;

import org.example.core.config.AbstractWebSecurityConfig;
import org.example.core.properties.ProjectSecurityProperties;
import org.example.core.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author tjstj
 * @description TODO
 * @date 2020/11/21 21:51
 */
@Configuration
@EnableWebSecurity
public class BrowserSecurityConfig extends AbstractWebSecurityConfig {

    @Autowired
    public ProjectSecurityProperties securityProperties;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        applyPasswordAuthenticationConfig(http);

        http.authorizeRequests()
                .antMatchers(
                        securityProperties.getSignInUrl(),
                        securityProperties.getSignOutUrl(),
                        securityProperties.getLoginPage(),
                        securityProperties.getLoginProcessUrl(),
                        securityProperties.getLoginMobileProcessUrl(),
                        securityProperties.getSignFailUrl()
                )
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable()
                .headers().frameOptions().sameOrigin();
    }
}
