package org.example.security.app.server;

import org.apache.commons.lang3.ArrayUtils;
//import org.example.security.core.properties.OAuth2ClientProperties;
//import org.example.security.core.properties.ProjectSecurityProperties;
import org.example.security.core.properties.OAuth2ClientProperties;
import org.example.security.core.properties.ProjectSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author tjstj
 * @description TODO
 * @date 2021/2/15 15:41
 */
@Configuration
@EnableAuthorizationServer
public class MyAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private ProjectSecurityProperties securityProperties;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Qualifier("accountUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * token存储
     */
    @Autowired
    private TokenStore tokenStore;

    @Autowired(required = false)
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired(required = false)
    private TokenEnhancer jwtTokenEnhancer;

    /**
     * 异常转换
     */
    @Autowired
    private WebResponseExceptionTranslator webResponseExceptionTranslator;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints
                .authenticationManager(authenticationManager)
                .tokenStore(jwtTokenStore())
                .accessTokenConverter(jwtAccessTokenConverter());

        endpoints
                .tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .exceptionTranslator(webResponseExceptionTranslator);
//         如果jwt转换器和jwt增强器都存在
        if (jwtAccessTokenConverter != null && jwtTokenEnhancer != null) {
            // 将其加入到增强链上
            TokenEnhancerChain enhancerChain = new TokenEnhancerChain();

            List<TokenEnhancer> enhancers = new ArrayList<>();
            enhancers.add(jwtAccessTokenConverter);
            enhancers.add(jwtTokenEnhancer);

            enhancerChain.setTokenEnhancers(enhancers);
            //jwt
            endpoints.tokenEnhancer(enhancerChain)
                    .accessTokenConverter(jwtAccessTokenConverter);

        }
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");

    }

    /**
     * 客户端配置
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
        if (ArrayUtils.isNotEmpty(securityProperties.getOauth2().getClients())) {
            for (OAuth2ClientProperties config : securityProperties.getOauth2().getClients()) {
                builder.withClient(config.getClientId())
                        .secret(passwordEncoder.encode(config.getClientSecret()))
                        .accessTokenValiditySeconds(config.getAccessTokenValidateSeconds())
                        .authorizedGrantTypes("refresh_token", "authorization_code", "password","implicit","client_credentials")
                        // 设置刷新令牌的过期时间
                        .refreshTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(1))
                        .scopes("all", "write", "read")
                        .autoApprove(true);;
            }
        }
    }


    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("challenge");
        return converter;
    }

}
