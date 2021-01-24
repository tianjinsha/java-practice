package org.example.browser.config;

import org.example.core.config.AbstractWebSecurityConfig;
import org.example.core.properties.ProjectSecurityProperties;
import org.example.core.properties.SecurityConstants;
import org.example.core.validate.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

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

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;

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
                .tokenValiditySeconds(securityProperties.getRememberMeSeconds())
                .userDetailsService(userDetailsService)
                .and()
                .sessionManagement()
                .invalidSessionStrategy(invalidSessionStrategy)
                .maximumSessions(securityProperties.getSession().getMaximumSessions())
                .maxSessionsPreventsLogin(securityProperties.getSession().isMaxSessionsPreventsLogin())
                .expiredSessionStrategy(sessionInformationExpiredStrategy)
                .and()
                .and()
                .authorizeRequests()
                .antMatchers(
                        securityProperties.getSignInUrl(),
                        securityProperties.getSignOutUrl(),
                        securityProperties.getLoginPage(),
                        securityProperties.getLoginProcessUrl(),
                        securityProperties.getLoginMobileProcessUrl(),
                        securityProperties.getSignFailUrl(),
                        securityProperties.getSession().getSessionInvalidPage(),
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
                        "/js/**",
                        "/css/**"
                )
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable()
                .headers().frameOptions().sameOrigin();
    }
}
