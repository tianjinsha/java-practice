package com.chengshi.train.social.qq.config;

import com.chengshi.train.properties.QQProperties;
import com.chengshi.train.properties.TrainSecurityProperties;
import com.chengshi.train.social.qq.connect.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

@Configuration
@ConditionalOnProperty(prefix = "trian.security.social.qq",name="app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    TrainSecurityProperties properties;
    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqConfig=properties.getSocial().getQq();
        return new QQConnectionFactory(qqConfig.getProviderId(), qqConfig.getAppId(), qqConfig.getAppSecret());
    }
}
