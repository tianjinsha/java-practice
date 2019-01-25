package com.chengshi.train.exception.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class ErrorController {

    @GetMapping("/404")
    public String page404(){
        log.info("404:访问 的也页面不存在！");
        return "/error/404";
    }

    @GetMapping("/405")
    public String page405(){
        System.out.println("This is 404 e");
        return "/error/401.html";
    }


}
