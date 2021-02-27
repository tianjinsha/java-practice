package org.example.security.core.properties;

import lombok.Data;

import java.util.concurrent.TimeUnit;

/**
 * @author tjstj
 * @description TODO
 * @date 2021/2/15 15:24
 */
@Data
public class OAuth2ClientProperties {

    /**
     * 第三方应用appId
     */
    private String clientId;
    /**
     * 第三方应用appSecret
     */
    private String clientSecret;
    /**
     * 针对此应用发出的token的有效时间
     */
    private int accessTokenValidateSeconds = (int) TimeUnit.HOURS.toSeconds(2);
}
