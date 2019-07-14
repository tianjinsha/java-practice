package com.chengshi.train.trainexception.controller;

import com.chengshi.train.trainexception.exception.LoginException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TrainController {

    @GetMapping("/say")
    public String say(){
        return "say";
    }

    @GetMapping("/hello")
    public String hello(){
        throw new LoginException("登陆错误！");
    }

    @GetMapping("/ex")
    public String ex() {
        throw new RuntimeException("系统错误");
    }
}
