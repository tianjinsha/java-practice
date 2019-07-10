package com.chengshi.train.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexControlelr {

    @ResponseBody
    @GetMapping("/ping")
    public String ping (){
        return "ping success !";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }
}
