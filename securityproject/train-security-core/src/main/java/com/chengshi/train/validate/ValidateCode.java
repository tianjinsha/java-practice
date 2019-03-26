package com.chengshi.train.validate;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *  验证码
 */
@Data
public class ValidateCode implements Serializable {
    /**
     * 验证码内容
     */
    private String code;
    /**
     * 验证码过期时间
     */
    private LocalDateTime expireTime;

    public ValidateCode(){}

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public ValidateCode(String code, int expireSec) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireSec);
    }

    /**
     * 判断验证码是否过期
     *
     * @return
     */
    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
