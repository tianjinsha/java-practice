package com.chengshi.train.trainexception.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@Slf4j
//@ControllerAdvice
public class GlobalExceptionHandler  {
    public static final String DEFAULT_ERROR_VIEW = "/exception";


    @ExceptionHandler(value = LoginException.class)
    public String defaultErrorHandler(Model model, HttpServletRequest req, Exception e){
        model.addAttribute("url",req.getRequestURI());
        model.addAttribute("exception",e);
        log.info("loginException:"+e.getMessage());
        return DEFAULT_ERROR_VIEW;
    }

    @ExceptionHandler(value = Exception.class)
    public String defaultErrorHandler2(Model model, HttpServletRequest req, Exception e){
        model.addAttribute("url",req.getRequestURI());
        model.addAttribute("exception",e);
        log.info("exception:"+e.getMessage());
        return DEFAULT_ERROR_VIEW;
    }
}
