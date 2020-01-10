package com.example.springboot;

import com.example.springboot.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class SpringbootApplication implements CommandLineRunner {

    @Autowired
    GoodService goodService;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=======");
        goodService.buy();
        System.out.println("=======");
    }
}
