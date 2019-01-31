package com.chengshi.train.config;

import com.chengshi.train.properties.TrainSecurityProperties;
import com.chengshi.train.session.TrainExpiredSessionStrategy;
import com.chengshi.train.session.TrainInvalidSessionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-31 23:44
 */
@Configuration
public class BrowserSecurityBeanConfig {

    @Autowired
    private TrainSecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(InvalidSessionStrategy.class)
    public InvalidSessionStrategy invalidSessionStrategy(){
        return new TrainInvalidSessionStrategy(securityProperties.getSession().getSessionInvalidUrl());
    }

    @Bean
    @ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
    public SessionInformationExpiredStrategy sessionInformationExpiredStrategy(){
        return new TrainExpiredSessionStrategy(securityProperties.getSession().getSessionInvalidUrl());
    }
}
