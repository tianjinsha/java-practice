package org.example.security.core.properties;

import lombok.Data;

/**
 * @author tjstj
 * @description TODO
 * @date 2021/1/24 16:03
 */
@Data
public class SmsCodeProperties {

    /**
     * 验证码长度
     */
    private int length = 6;
    /**
     * 验证码过期时间(分钟)
     */
    private int expireIn = 60;

    private String url;

}
