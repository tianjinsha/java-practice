package com.chengshi.train.social.qq.config;

import com.chengshi.train.properties.TrainSecurityProperties;
import com.chengshi.train.properties.QQProperties;
import com.chengshi.train.social.TrainConnectView;
import com.chengshi.train.social.qq.connect.QQConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.web.servlet.View;

import javax.sql.DataSource;

@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "train.security.social.qq",name="app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired(required = false)
    private ConnectionSignUp connectionSignUp;


    @Autowired
    TrainSecurityProperties properties;
    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qq=properties.getSocial().getQq();
        return new QQConnectionFactory(qq.getProviderId(), qq.getAppId(), qq.getAppSecret());
    }

        @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,
                connectionFactoryLocator, Encryptors.noOpText());
        repository.setTablePrefix("qxs_");
        if(connectionSignUp != null) {
            log.info("connection2");
            repository.setConnectionSignUp(connectionSignUp);
        }
        return repository;
    }

    @Bean({"connect/qqConnect", "connect/qqConnected"})
    @ConditionalOnMissingBean(name = "qqConnectedView")
    public View qqConnectedView() {
        return new TrainConnectView();
    }

}
