package com.cehngshi.train.exception.controller;

import com.cehngshi.train.exception.TrainSecurityException;
import com.cehngshi.train.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class TrainExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = TrainSecurityException.class)
    public R defaultErrorHandler(TrainSecurityException e){
        R<String> r=new R<>();
        r.setCode(R.FAIL);
        r.setMsg(e.getMessage());
        log.info("exception:"+e.getMessage());
        return r;
    }
}
