package org.example.security.core.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.core.constant.CommonErrorCodeBase;
import org.example.security.core.properties.ResponseType;
import org.example.security.core.properties.ProjectSecurityProperties;
import org.example.core.protocol.CommonBean;
import org.example.core.protocol.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: tjshan
 * @date: 2021/1/17 3:00 下午
 * FileName: LogoutsSuccessHandler
 * Description:
 */
@Slf4j
@Component
public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProjectSecurityProperties securityProperties;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (ResponseType.JSON.name().equals(securityProperties.getResponseType())) {
            response.setHeader("Content-Type", "application/json;charset=utf-8");
            CommonResult result = new CommonResult();
            CommonBean bean = new CommonBean(CommonErrorCodeBase.SUCCESS,"注销成功！");
            result.setParam(bean);
            result.setCommand(securityProperties.getSignOutUrl());

            response.getWriter().write(objectMapper.writeValueAsString(result));
            response.getWriter().flush();
        }else{
            setDefaultTargetUrl(securityProperties.getSignInUrl());
            super.onLogoutSuccess(request,response,authentication);
        }
    }
}
