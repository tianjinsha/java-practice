package com.train.rabbitmq.simplemodel;

import org.springframework.context.annotation.Configuration;

import java.util.Queue;

/**
 * @author tjshan
 * @since 2019/7/9 10:34
 */
@Configuration
public class RabbitConfig {

    private final static String QUEUE_NAME="queue_test";

    public Queue string(){

    }
}
