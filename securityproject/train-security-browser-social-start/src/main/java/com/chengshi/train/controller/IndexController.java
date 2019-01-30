package com.chengshi.train.controller;

import com.chengshi.train.properties.TrainSecurityProperties;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    TrainSecurityProperties properties;
    @GetMapping("/ping")
    public String ping(){
        return "ping success !";
    }

    @PreAuthorize("hasAuthority('ROLE_BACK')")
    @GetMapping("/hello")
    public String hello(){
        return "hello hello hello !";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("admin")
    public Object login() {
        return  "admin";
    }

    @GetMapping("/ex")
    public String ex(){
        throw new RuntimeException("运行错误");
    }

}
