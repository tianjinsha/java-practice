package com.chengshi.train.exception.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class ErrorController {

    @GetMapping("/404")
    public String page404(){
        log.info("404:访问的也页面不存在！");
        return "/error/404.html";
    }

    @GetMapping("/401")
    public String page401(){
        log.info("401:你还没有登陆！");
        return "/error/401.html";
    }


}
