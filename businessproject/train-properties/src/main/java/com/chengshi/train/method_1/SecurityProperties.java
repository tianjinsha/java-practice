package com.chengshi.train.method_1;

import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-23 21:16
 */
@Data
@ConfigurationProperties(prefix = "train.security")
public class SecurityProperties {

    private BrowserProperties browser=new BrowserProperties();

    private int time;

}
