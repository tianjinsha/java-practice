package com.chengshi.train.validate.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码错误异常
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
