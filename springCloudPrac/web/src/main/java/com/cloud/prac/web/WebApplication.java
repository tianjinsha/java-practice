package com.cloud.prac.web;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class WebApplication {

    @Bean
    Logger.Level feignLoggerLever(){
        return Logger.Level.FULL;
    }

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

}
