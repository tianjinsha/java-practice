package org.example.security.app.server;

import org.example.security.app.jwt.MyTokenEnhancer;
//import org.example.security.core.properties.ProjectSecurityProperties;
import org.example.security.core.properties.ProjectSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author tjstj
 * @description TODO
 * @date 2021/2/15 16:47
 */
//@Configuration
public class TokenStoreConfig {

    /**
     * redis连接工厂
     */
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;


    /**
     * 使用redis存储token的配置，只有在project.security.oauth2.tokenStore配置为redis时生效
     * @return RedisTokenStore
     */
    @Bean
    @ConditionalOnProperty(prefix = "project.security.oauth2",
            name = "storeType",
            havingValue = "redis")
    public TokenStore redisTokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    /**
     * jwt的配置
     *
     * 使用jwt时的配置，默认生效
     */
    @Configuration
    @ConditionalOnProperty(prefix = "project.security.oauth2",
            name = "storeType",
            havingValue = "jwt",
            matchIfMissing = true)
    public static class JwtTokenConfig {

        @Autowired
        private ProjectSecurityProperties securityProperties;

        @Bean
        public TokenStore jwtTokenStore() {
            return new JwtTokenStore(jwtAccessTokenConverter());
        }

        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter() {
            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
            converter.setSigningKey("security");
            converter.setSigningKey(securityProperties.getOauth2().getJwtSigningKey());
            return converter;
        }

        @Bean
        @ConditionalOnMissingBean(name = "jwtTokenEnhancer")
        public TokenEnhancer jwtTokenEnhancer(){
            return new MyTokenEnhancer();
        }
    }
}
