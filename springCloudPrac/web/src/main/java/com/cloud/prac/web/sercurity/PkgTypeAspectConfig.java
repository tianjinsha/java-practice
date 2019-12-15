package com.cloud.prac.web.sercurity;

import com.cloud.prac.web.service.AuthService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author tjshan
 * @date 2019/10/4 13:14
 */
@Aspect
@Component
public class PkgTypeAspectConfig {

    @Autowired
    AuthService authService;

    @Pointcut("within(com.cloud.prac.web.service.ProductService)")
    public void matchType(){

    }

    @Before("matchType()")
    public void before(){
        authService.checkAccess();
        System.out.println("############within############");
    }
}
