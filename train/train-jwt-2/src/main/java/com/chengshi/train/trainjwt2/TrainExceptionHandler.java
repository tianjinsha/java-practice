package com.chengshi.train.trainjwt2;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class TrainExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = ServletException.class)
    public Object defaultExceptionHander(ServletException e){
        Map map=new HashMap();
        map.put("code",403);
        map.put("msg",e.getMessage());
        return map;
    }
}
