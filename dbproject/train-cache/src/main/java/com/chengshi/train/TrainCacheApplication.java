package com.chengshi.train;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class TrainCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrainCacheApplication.class, args);
    }

}

