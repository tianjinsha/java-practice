package com.chengshi.shiro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @ResponseBody
    @GetMapping("/ping")
    public String ping (){
        return "ping success !";
    }
}
