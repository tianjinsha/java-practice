package org.example.core.validate;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author tjstj
 * @description 验证码
 * @date 2021/1/24 15:17
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
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
