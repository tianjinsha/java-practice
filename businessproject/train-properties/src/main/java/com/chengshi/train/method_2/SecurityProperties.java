package com.chengshi.train.method_2;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-23 21:16
 */
@Data
@Component("securityProperties2")
@ConfigurationProperties(prefix = "train.security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

    private int time;

}
