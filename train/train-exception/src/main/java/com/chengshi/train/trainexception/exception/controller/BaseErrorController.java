package com.chengshi.train.trainexception.exception.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
//@Controller
//@RequestMapping("/error")
public class BaseErrorController implements ErrorController {
    @Override
    public String getErrorPath() {
        log.info("出错啦！进入自定义错误控制器");
        return "templates/error/error";
    }

    @RequestMapping
    public String error() {
        return getErrorPath();
    }
}
