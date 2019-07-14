package com.chengshi.train;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TrainTimeingApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrainTimeingApplication.class, args);
    }

}

