package com.chengshi.train.validate.image;

import com.chengshi.train.properties.TrainSecurityProperties;
import com.chengshi.train.validate.ValidateCode;
import com.chengshi.train.validate.ValidateCodeGenerator;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.image.BufferedImage;

@Slf4j
@Component("imageValidateCodeGenerator")
public class kpatchaImageValidateCodeGenerator implements ValidateCodeGenerator {
    /**
     * 系统配置
     */
    @Autowired
    private TrainSecurityProperties properties;

    @Autowired
    private DefaultKaptcha captchaProducer;

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String sRand = captchaProducer.createText();
        log.info(sRand);
        BufferedImage image = captchaProducer.createImage(sRand);
        return new ImageCode(image, sRand, properties.getCode().getImage().getExpireIn());
    }
}
