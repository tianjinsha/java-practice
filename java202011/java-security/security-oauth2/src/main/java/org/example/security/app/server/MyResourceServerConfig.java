package org.example.security.app.server;

//import org.example.security.core.authentication.AccessDeniedHandler;
//import org.example.security.core.authentication.AuthenticationFailureHandler;
//import org.example.security.core.properties.ProjectSecurityProperties;
import org.example.security.core.authentication.AccessDeniedHandler;
import org.example.security.core.properties.ProjectSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

/**
 * @author tjstj
 * @description TODO
 * @date 2021/2/15 16:18
 */
@Configuration
@EnableResourceServer
public class MyResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    ProjectSecurityProperties securityProperties;

    /**
     * 没有登录、token异常
     */
    @Qualifier("appOAuth2AuthenticationEntryPoint")
    @Autowired
    private OAuth2AuthenticationEntryPoint authenticationEntryPoint;
    /**
     * 权限不足
     */
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    /**
     * 异常转换
     */
    @Autowired
    private WebResponseExceptionTranslator webResponseExceptionTranslator;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        authenticationEntryPoint.setExceptionTranslator(webResponseExceptionTranslator);
        resources.authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
    }
}
