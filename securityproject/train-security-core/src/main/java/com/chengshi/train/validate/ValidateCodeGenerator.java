package com.chengshi.train.validate;

import org.springframework.web.context.request.ServletWebRequest;

/**
 *
 * 验证码生成器
 */
public interface ValidateCodeGenerator {
    public ValidateCode generate(ServletWebRequest servletWebRequest);
}
