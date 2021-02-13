package org.example.security.core.validate.sms;

import org.apache.commons.lang3.RandomStringUtils;
import org.example.security.core.properties.ProjectSecurityProperties;
import org.example.security.core.validate.ValidateCode;
import org.example.security.core.validate.ValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author tjstj
 * @description TODO
 * @date 2021/1/30 23:15
 */
@Component("smsValidateCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private ProjectSecurityProperties securityProperties;
    @Override
    public ValidateCode generate(ServletWebRequest servletWebRequest) {
        int len = securityProperties.getCode().getSms().getLength();
        String code = RandomStringUtils.randomNumeric(len);
        return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
    }
}
