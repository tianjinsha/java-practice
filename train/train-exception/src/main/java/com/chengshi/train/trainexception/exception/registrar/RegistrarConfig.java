package com.chengshi.train.trainexception.exception.registrar;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegistrarConfig {
//    @Bean
//    public ErrorPageRegistrar errorPageRegistrar(){
//        return new TrainErrorPageRegistrar();
//    }


    @Bean
    public EmbeddedServletContainerCustomizer customizer(){
        return new CustomizationBean();
    }


}
