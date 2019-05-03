package com.chengshi.train.exception.controller;

import com.chengshi.train.exception.LoginException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:定义全局错误
 * @author: tian
 * @date: 2019-01-21 22:56
 */
@Slf4j
@ControllerAdvice
public class RedirectExceptionHandler {

    public static final String DEFAULT_ERROR_VIEW = "/error/exception";

    @ExceptionHandler(value = LoginException.class)
    public String defaultErrorHandler(Model model, HttpServletRequest req, Exception e) {
        model.addAttribute("url", req.getRequestURI());
        model.addAttribute("exception", e);
        log.info("loginException:" + e.getMessage());
        return DEFAULT_ERROR_VIEW;
    }
}
