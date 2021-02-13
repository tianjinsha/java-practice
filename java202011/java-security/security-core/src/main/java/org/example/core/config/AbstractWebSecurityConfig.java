package org.example.core.config;

import org.example.core.authentication.AccessDeniedHandler;
import org.example.core.authentication.AuthenticationFailureHandler;
import org.example.core.authentication.AuthenticationSuccessHandler;
import org.example.core.authentication.LogoutSuccessHandler;
import org.example.core.properties.ProjectSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @author tjstj
 * @description TODO
 * @date 2020/11/21 22:35
 */
public class AbstractWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ProjectSecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private DataSource dataSource;

    @Qualifier("accountUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //添加自定义的userDetailsService认证
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
        http.formLogin()
                //登录页
                .loginPage(securityProperties.getLoginPage())
                //登录处理接口
                .loginProcessingUrl(securityProperties.getLoginProcessUrl())
                //登录成功处理器
                .successHandler(authenticationSuccessHandler)
                //登录失败处理器
                .failureHandler(authenticationFailureHandler)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .logout()
                //退出路径
                .logoutUrl(securityProperties.getSignOutUrl())
                //登出成功处理器
                .logoutSuccessHandler(logoutSuccessHandler)
                //删除session
                .deleteCookies("JSESSIONID")
        ;
    }

    /**
     * 记住我处理
     * @return JdbcTokenRepositoryImpl
     */
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        tokenRepository.setCreateTableOnStartup(false);
        return tokenRepository;
    }
}
