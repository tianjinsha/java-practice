package com.chengshi.train.exception.controller;

import com.chengshi.train.exception.LoginException;
import com.chengshi.train.support.R;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-21 23:03
 */
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = LoginException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R<String> jsonErrorHandler(HttpServletRequest req, LoginException e) {
        R<String> r=new R<>();
        r.setCode(R.FAIL);
        r.setMsg(e.getMessage());
        return r;
    }
}
