package com.chengshi.train.method_1.config;

import com.chengshi.train.method_1.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-23 21:27
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityConfig {
}
