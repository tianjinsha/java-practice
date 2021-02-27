package org.example.security.core.validate.image;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.slf4j.Slf4j;
import org.example.security.core.properties.ProjectSecurityProperties;
import org.example.security.core.validate.ValidateCode;
import org.example.security.core.validate.ValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.image.BufferedImage;

/**
 * @author tjstj
 * @description TODO
 * @date 2021/1/24 15:31
 */
@Slf4j
@Component
public class ImageCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    ProjectSecurityProperties securityProperties;

    @Autowired
    private DefaultKaptcha captchaProducer;

    @Override
    public ValidateCode generate(ServletWebRequest servletWebRequest) {
        String sRand = captchaProducer.createText();
        log.info( "image code:"+sRand);
        BufferedImage image = captchaProducer.createImage(sRand);
        return new ImageCode(image, sRand, securityProperties.getCode().getImage().getExpireIn());
    }
}
