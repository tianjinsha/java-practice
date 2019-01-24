package com.chengshi.train.method_3;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-23 21:16
 */
@Data
@Component("securityProperties3")
public class SecurityProperties {

    @Value("${train.security.browser.signUpUrl}")
    private String signUpUrl;

    @Value("${train.security.browser.loginProcessingUrl}")
    private String loginProcessingUrl;

    @Value("${train.security.time}")
    private Integer time;

}
