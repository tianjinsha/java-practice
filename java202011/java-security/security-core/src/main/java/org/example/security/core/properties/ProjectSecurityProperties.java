package org.example.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: tjshan
 * @date: 2021/1/17 1:39 下午
 * FileName: ProjectSecurityProperties
 * Description:
 */
@Data
@Component
@ConfigurationProperties(prefix = "project.security")
public class ProjectSecurityProperties {

    private SessionProperties session = new SessionProperties();

    private ValidateCodeProperties code = new ValidateCodeProperties();

    private JwtProperties jwt = new JwtProperties();

    /**
     * OAuth2认证服务器配置
     */
    private OAuth2Properties oauth2 = new OAuth2Properties();


    private String responseType = ResponseType.JSON.name();

    private String signInUrl=SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

    private String signUpUrl = SecurityConstants.DEFAULT_REGISTER_PAGE_URL;

    private String signOutUrl =SecurityConstants.DEFAULT_LOGOUT_PAGE_URL;

    private String signFailUrl =SecurityConstants.DEFAULT_LOGIN_FAIL_PAGE_URL;

    private String loginPage= SecurityConstants.DEFAULT_UN_AUTHENTICATION_URL;

    private String loginProcessUrl=SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM;

    private String loginMobileProcessUrl = SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE;

    private int rememberMeSeconds=7200;

};
