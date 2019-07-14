package com.chengshi.train.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
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
}
