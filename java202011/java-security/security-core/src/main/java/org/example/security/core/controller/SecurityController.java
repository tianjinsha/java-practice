package org.example.security.core.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.core.constant.CommonErrorCodeBase;
import org.example.core.constant.ResultEnum;
import org.example.security.core.properties.ProjectSecurityProperties;
import org.example.security.core.properties.ResponseType;
import org.example.core.protocol.CommonBean;
import org.example.core.protocol.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: tjshan
 * @date: 2021/1/17 2:39 下午
 * FileName: SecurityControl
 * Description:
 */
@Slf4j
@RestController
public class SecurityController {

    @Autowired
    private ProjectSecurityProperties securityProperties;

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    CommonResult requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DefaultSavedRequest savedRequest = (DefaultSavedRequest) requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("认证失败，引发跳转的请求是:" + targetUrl);
            if (securityProperties.getResponseType().equals(ResponseType.HTML.name())) {
                redirectStrategy.sendRedirect(request, response, securityProperties.getSignInUrl());
            }
        }
        CommonResult result = new CommonResult();
        int a= ResultEnum.Fail.getResult();
        String b= ResultEnum.Fail.getMessage();
        result.setResult(ResultEnum.Fail.getResult());
        result.setMessage(ResultEnum.Fail.getMessage());
        String service = savedRequest.getRequestURI();
        result.setCommand(service);
        CommonBean bean = new CommonBean(CommonErrorCodeBase.UNAUTHORIZED,"认证失败!");
        result.setParam(bean);
        return  result;
    }

}
