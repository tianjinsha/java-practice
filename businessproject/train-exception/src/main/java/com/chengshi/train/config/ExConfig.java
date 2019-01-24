package com.chengshi.train.config;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.util.HashSet;
import java.util.Set;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-21 22:40
 */
@Configuration
public class ExConfig {
    /**
     * 自定义错误页面
     * @return
     */
//    @Bean
//    public ErrorPageRegistrar errorPageRegistrar(){
//        return registry -> {
//            ErrorPage page404 = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
//            ErrorPage page403 = new ErrorPage(HttpStatus.FORBIDDEN, "/403");
//            ErrorPage page500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500");
//            registry.addErrorPages(page404, page500);
//        };
//    }

    /**
     * 自定义错误页面
     * @return
     */
    @Bean
    public EmbeddedServletContainerCustomizer customizer() {

        return container -> {
            Set<ErrorPage> errorPages = new HashSet<>();
            ErrorPage page404 = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
            ErrorPage page403 = new ErrorPage(HttpStatus.FORBIDDEN, "/403");
            ErrorPage page500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500");

            errorPages.add(page403);
            errorPages.add(page404);
            errorPages.add(page500);
            container.setErrorPages(errorPages);
        };
    }
}
