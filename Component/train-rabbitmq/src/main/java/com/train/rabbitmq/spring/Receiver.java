package com.train.rabbitmq.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接受者
 * @author tjshan
 * @since 2019/7/12 9:14
 */
@Component
@RabbitListener(queues = "hello")
@Slf4j
public class Receiver {
    @RabbitHandler
    public void process(String hello){
        log.info("**********Receiver**********:"+hello);
    }
}
