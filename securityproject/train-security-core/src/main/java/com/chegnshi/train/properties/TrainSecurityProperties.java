package com.chegnshi.train.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Data
@Component
@ConfigurationProperties(prefix = "train.security")
public class TrainSecurityProperties {

    private String loginType="JSON";

    private String loginPage="/authentication/require";

    private String loginProcessUrl="/authentication/form";

    private String signUpUrl="/signIn.html";

    private int rememberMeSeconds=7200;
}
