package com.train.elasticsearch.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * 发送者
 * @author tjshan
 * @since 2019/7/12 9:04
 */
@Component
@Slf4j
public class Sender {

    @Autowired
    private AmqpTemplate template;

    public void send() throws InterruptedException {
        for (int i=0;i<30;i++){
            String context="Hello:"+ LocalDateTime.now();
            log.info("**********Sender**********:"+context);
            TimeUnit.SECONDS.sleep(1);
            this.template.convertAndSend("hello",context);
        }
    }
}
