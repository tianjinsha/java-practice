package com.cheng.train.util.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("train.security")
public class SecurityProperties {

    private String loginType="REDIRECT";

    private String loginPage="/authentication/require";

    private String loginProcessUrl="/authentication/form";

    private String signUpUrl="/login.html";


}
