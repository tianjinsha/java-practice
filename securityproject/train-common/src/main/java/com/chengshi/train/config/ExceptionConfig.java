package com.chengshi.train.config;

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
            ErrorPage page401=new ErrorPage(HttpStatus.UNAUTHORIZED,"/error/401.html");
            ErrorPage page403 = new ErrorPage(HttpStatus.FORBIDDEN, "/error/403.html");
//            ErrorPage page404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404.html");
            ErrorPage page500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500.html");
            registry.addErrorPages(page401,page403, page500);
        };
    }
}
