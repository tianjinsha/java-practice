package org.example.security.browser.config;

import org.example.security.core.properties.ProjectSecurityProperties;
import org.example.security.browser.session.OwnExpiredSessionStrategy;
import org.example.security.browser.session.OwnInvalidSessionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

/**
 * @author tjstj
 * @description TODO
 * @date 2021/1/24 13:59
 */
@Configuration
public class BrowserSecurityBeanConfig {

    @Autowired
    private ProjectSecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(InvalidSessionStrategy.class)
    public InvalidSessionStrategy invalidSessionStrategy(){
        return new OwnInvalidSessionStrategy(securityProperties.getSignInUrl());
    }

    @Bean
    @ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
    public SessionInformationExpiredStrategy sessionInformationExpiredStrategy(){
        return new OwnExpiredSessionStrategy(securityProperties.getSignInUrl());
    }
}
