package com.chengshi.train.social.qq.config;

import com.chengshi.train.properties.TrainSecurityProperties;
import com.chengshi.train.properties.QQProperties;
import com.chengshi.train.social.qq.connect.QQConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "train.security.social.qq",name="app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    TrainSecurityProperties properties;
    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qq=properties.getSocial().getQq();
        return new QQConnectionFactory(qq.getProviderId(), qq.getAppId(), qq.getAppSecret());
    }
}
