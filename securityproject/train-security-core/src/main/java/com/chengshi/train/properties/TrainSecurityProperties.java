package com.chengshi.train.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Data
@Component
@ConfigurationProperties(prefix = "train.security")
public class TrainSecurityProperties {

    private JwtProperties jwt=new JwtProperties();

    private ValidateCodeProperties code=new ValidateCodeProperties();

    private TrainSocialProperties social=new TrainSocialProperties();
    private String loginType="JSON";

    private String signUpUrl = "/signUp.html";

    private String loginPage=SecurityConstants.DEFAULT_UNAUTHENTICATION_URL;

    private String loginProcessUrl=SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM;

    private String signInUrl=SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

    private int rememberMeSeconds=7200;

}
