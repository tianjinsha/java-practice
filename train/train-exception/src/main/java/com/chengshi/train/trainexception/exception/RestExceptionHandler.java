package com.chengshi.train.trainexception.exception;


import com.chengshi.train.trainexception.util.R;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestExceptionHandler {

//    @ExceptionHandler(value = LoginException.class)
//    @ResponseBody
//    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, LoginException e) throws Exception {
//        ErrorInfo<String> r = new ErrorInfo<>();
//        r.setMessage(e.getMessage());
//        r.setCode(ErrorInfo.ERROR);
//        r.setData("Some Data");
//        r.setUrl(req.getRequestURL().toString());
//        return r;
//    }

    @ExceptionHandler(value = LoginException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R<String> jsonErrorHandler(HttpServletRequest req, LoginException e) throws Exception {

        R<String> r=new R<>();
        r.setCode(R.FAIL);
        r.setMsg(e.getMessage());
        return r;
    }
}
