package org.example.security.core.validate;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author tjstj
 * @description 验证码生成器
 * @date 2021/1/24 15:22
 */
public interface ValidateCodeGenerator {
    /**
     * 生成一个验证码
     * @param servletWebRequest
     * @return ValidateCode
     */
     ValidateCode generate(ServletWebRequest servletWebRequest);
}
