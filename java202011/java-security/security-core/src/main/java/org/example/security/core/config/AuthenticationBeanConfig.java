package org.example.security.core.config;

import org.example.security.core.authentication.DefaultSocialUserDetailsService;
import org.example.security.core.authentication.DefaultUserDetailsService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUserDetailsService;

/**
 * @author tjstj
 * @description 模块默认的配置
 * @date 2021/2/15 19:14
 * 认证相关的扩展点配置。配置在这里的bean，业务系统都可以通过声明同类型或同名的bean来覆盖安全
 */
@Configuration
public class AuthenticationBeanConfig {

    /**
     * 默认认证器
     *
     * @return UserDetailsService
     */
    @Bean(name = "accountUserDetailsService")
    @ConditionalOnMissingBean(name = "accountUserDetailsService")
    public UserDetailsService formDetailsService() {
        return new DefaultUserDetailsService();
    }

    /**
     * 默认认证器
     *
     * @return
     */
    @Bean(name = "mobileDetailsService")
    @ConditionalOnMissingBean(name = "mobileDetailsService")
    public UserDetailsService mobileDetailsService() {
        return new DefaultUserDetailsService();
    }

    /**
     * 默认认证器
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(SocialUserDetailsService.class)
    public SocialUserDetailsService socialUserDetailsService() {
        return new DefaultSocialUserDetailsService();
    }

    /**
     * 默认密码处理器
     * @return
     */
    @Bean
    @ConditionalOnMissingClass
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}
