package org.example.security.core.properties;

import lombok.Data;

/**
 * @author tjstj
 * @description TODO
 * @date 2021/2/15 15:23
 */
@Data
public class OAuth2Properties {

    /**
     * 客户端配置
     */
    private OAuth2ClientProperties[] clients = {};

    /**
     * jwt的签名
     */
    private String jwtSigningKey = "project";
}
