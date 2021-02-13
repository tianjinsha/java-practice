package org.example.security.app.config;

import org.example.security.core.authentication.AccessDeniedHandler;
import org.example.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import org.example.security.core.config.AbstractWebSecurityConfig;
import org.example.security.core.properties.ProjectSecurityProperties;
import org.example.security.core.properties.SecurityConstants;
import org.example.security.core.validate.ValidateCodeSecurityConfig;
import org.example.security.app.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author tjstj
 * @description TODO
 * @date 2021/1/31 22:09
 */
@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends AbstractWebSecurityConfig {

    @Autowired
    public ProjectSecurityProperties securityProperties;

    @Qualifier("accountUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;




    @Override
    protected void configure(HttpSecurity http) throws Exception {

        applyPasswordAuthenticationConfig(http);

        http
                .apply(validateCodeSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .authorizeRequests()
                .antMatchers(
                        securityProperties.getLoginProcessUrl(),
                        securityProperties.getLoginMobileProcessUrl(),
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*"
                )
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();

        http.exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .and()
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
