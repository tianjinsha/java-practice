package com.chengshi.train.exception;

import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ErrorPageRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class ExceptionConfig {

    /**
     * 自定义错误页面
     * @return
     */
    @Bean
    public ErrorPageRegistrar errorPageRegistrar(){
        return registry -> {
            ErrorPage page401=new ErrorPage(HttpStatus.UNAUTHORIZED,"/401");
            ErrorPage page404 = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
            ErrorPage page403 = new ErrorPage(HttpStatus.FORBIDDEN, "/403");
            ErrorPage page500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500");
            registry.addErrorPages(page401,page403,page404, page500);
        };
    }
}
