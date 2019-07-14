package com.chengshi.train.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-21 22:13
 */
@Controller
public class IndexController {

    @ResponseBody
    @GetMapping("/ping")
    public String ping() {
        return "ping success !";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }


    @GetMapping("/say")
    public String say() {
        return "say";
    }
}
