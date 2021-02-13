package org.example.security.core.validate;

import org.example.security.core.validate.image.ImageCodeGenerator;
import org.example.security.core.validate.sms.DefaultSmsCodeSender;
import org.example.security.core.validate.sms.SmsCodeSender;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tjstj
 * @description TODO
 * @date 2021/1/24 15:31
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Bean
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator() {
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        return codeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSender();
    }
}
