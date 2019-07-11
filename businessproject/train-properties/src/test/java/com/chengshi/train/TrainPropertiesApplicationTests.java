package com.chengshi.train;

import com.chengshi.train.method_1.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TrainPropertiesApplicationTests {

    @Autowired
    SecurityProperties securityProperties;

    @Autowired
    com.chengshi.train.method_2.SecurityProperties securityProperties2;
    @Autowired
    com.chengshi.train.method_3.SecurityProperties securityProperties3;

    @Test
    public void TestMethod_1() {
        log.info("url:" + securityProperties.getBrowser().getSignUpUrl());
        log.info("time:" + securityProperties.getTime());
    }

    @Test
    public void TestMethod_2() {
        log.info("url:" + securityProperties2.getBrowser().getSignUpUrl());
        log.info("time:" + securityProperties2.getTime());
    }

    @Test
    public void TestMethod_3() {
        log.info("url:" + securityProperties3.getSignUpUrl());
        log.info("processing:" + securityProperties3.getLoginProcessingUrl());
        log.info("time:" + securityProperties3.getTime());
    }

}

