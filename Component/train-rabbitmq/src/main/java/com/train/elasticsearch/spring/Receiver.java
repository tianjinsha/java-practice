package com.train.elasticsearch.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

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
    public void process(String hello) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        log.info("**********Receiver**********:"+hello);
    }
}
