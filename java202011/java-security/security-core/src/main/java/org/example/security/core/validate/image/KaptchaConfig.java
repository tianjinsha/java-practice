package org.example.security.core.validate.image;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.example.security.core.properties.ProjectSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author tjstj
 * @description TODO
 * @date 2021/1/24 15:56
 */
@Configuration
public class KaptchaConfig {

    @Autowired
    private ProjectSecurityProperties securityProperties;

    @Bean
    public DefaultKaptcha getDefaultKaptcha() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border", "no");
        properties.setProperty("kaptcha.textproducer.font.color", "black");
        properties.setProperty("kaptcha.image.width", securityProperties.getCode().getImage().getWidth()+"");
        properties.setProperty("kaptcha.image.height", securityProperties.getCode().getImage().getHeight()+"");
        properties.setProperty("kaptcha.textproducer.char.space", "5");
        properties.setProperty("kaptcha.textproducer.font.size", "30");
        properties.setProperty("kaptcha.session.key", "code");
        properties.setProperty("kaptcha.textproducer.char.length", securityProperties.getCode().getImage().getLength()+"");
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
