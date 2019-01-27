package com.chengshi.train.properties;

import lombok.Data;

@Data
public class JwtProperties {

    /**
     * 秘钥
     */
    private String secret="secret";

    /**
     * 有效期
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
