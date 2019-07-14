package com.train.rabbitmq.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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

    public void send(){
        String context="Hello:"+ LocalDateTime.now();
        log.info("**********Sender**********:"+context);
        this.template.convertAndSend("hello",context);
    }
}
