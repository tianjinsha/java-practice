package com.chengshi.train.trainexception.exception.registrar;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.http.HttpStatus;

import java.util.HashSet;
import java.util.Set;

public class CustomizationBean implements EmbeddedServletContainerCustomizer {
    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        Set<ErrorPage> errorPages = new HashSet<>();
        ErrorPage page404 = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
        ErrorPage page403 = new ErrorPage(HttpStatus.FORBIDDEN, "/403");
        ErrorPage page500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500");

        errorPages.add(page403);
        errorPages.add(page404);
        errorPages.add(page500);
        container.setErrorPages(errorPages);
    }
}
