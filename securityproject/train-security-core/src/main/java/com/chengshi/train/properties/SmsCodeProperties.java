package com.chengshi.train.properties;

import lombok.Data;

/**
 * 短信验证码的相关属性
 */
@Data
public class SmsCodeProperties {
    /**
     * 验证码长度
     */
    private int length = 6;
    /**
     * 验证码过期时间
     */
    private int expireIn = 60;
    /**
     * 验证码的生成地址
     */
    private String url;
}
