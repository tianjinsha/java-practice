package com.chengshi.train.config;


import com.chengshi.train.properties.SecurityConstants;
import com.chengshi.train.properties.TrainSecurityProperties;
import com.chengshi.train.validate.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

    @Autowired
    private TrainSecurityProperties trainSecurityProperties;

    @Autowired
    private UserDetailsService trainUserDetailsService;

    /**
     * 验证码配置器
     */
    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        applyPasswordAuthenticationConfig(http);

        http
                .apply(validateCodeSecurityConfig)
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(trainSecurityProperties.getRememberMeSeconds())
                .userDetailsService(trainUserDetailsService)
                .and()
                .authorizeRequests()
                .antMatchers(
                        trainSecurityProperties.getSignUpUrl(),
                        trainSecurityProperties.getLoginProcessUrl(),
                        trainSecurityProperties.getLoginPage(),
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
                        "/logout","/js/**", "/images/**", "/css/**", "/resources/**","/error/401.html").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }
}
