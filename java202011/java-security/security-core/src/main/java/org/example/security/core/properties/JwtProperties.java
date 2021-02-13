package org.example.security.core.properties;

import lombok.Data;

/**
 * @author tjstj
 * @description TODO
 * @date 2021/1/31 22:27
 */
@Data
public class JwtProperties {

    /**
     * 秘钥
     */
    private String secret="secret";

    /**
     * 有效期(s)
     */
    private int expiration=7200;

    /**
     * 头信息
     */
    private String header="Authorization";

    /**
     * token开始字符
     */
    private String tokenHead="Bearer ";
}
