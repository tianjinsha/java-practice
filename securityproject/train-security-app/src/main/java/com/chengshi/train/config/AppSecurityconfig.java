package com.chengshi.train.config;


import com.chengshi.train.Authentication.AppAuthenticationSuccessHandler;
import com.chengshi.train.authentication.TrainAccessDeniedHandler;
import com.chengshi.train.filter.JwtAuthenticationTokenFilter;
import com.chengshi.train.properties.TrainSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityconfig extends AbstractChannelSecurityConfig {

    @Autowired
    private TrainSecurityProperties trainSecurityProperties;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private TrainAccessDeniedHandler trainAccessDeniedHandler;

    @Autowired
    private AppAuthenticationSuccessHandler appAuthenticationSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        applyPasswordAuthenticationConfig(http);
        http
                .formLogin()
                .successHandler(appAuthenticationSuccessHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(trainSecurityProperties.getLoginPage(),
                        trainSecurityProperties.getLoginProcessUrl(),
                        trainSecurityProperties.getSignInUrl(),
                        "/logout", "/js/**", "/images/**", "/css/**", "/static/**").permitAll()
                .anyRequest().authenticated()

                .and()
                .csrf().disable();

        //jwt过滤器
        http.exceptionHandling()
                .accessDeniedHandler(trainAccessDeniedHandler)
                .and().addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
