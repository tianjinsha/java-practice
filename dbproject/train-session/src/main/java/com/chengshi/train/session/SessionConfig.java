package com.chengshi.train.session;


import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;

//@Configuration
public class SessionConfig {

    //设置session失效时间
    @Bean
    EmbeddedServletContainerCustomizer containerCustomizer(){
        return container -> {
            //设置session过期时间，单位（s）
            container.setSessionTimeout(1800);
        };
    }
}
