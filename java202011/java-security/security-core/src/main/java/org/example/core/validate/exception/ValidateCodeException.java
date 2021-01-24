package org.example.core.validate.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author tjstj
 * @description 验证码错误异常
 * @date 2021/1/24 15:19
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
