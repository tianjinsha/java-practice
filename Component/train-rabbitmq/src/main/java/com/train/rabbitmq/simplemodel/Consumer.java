package com.train.rabbitmq.simplemodel;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者
 * @author tjshan
 * @since 2019/7/9 10:24
 */
public class Consumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        final String QUEUE_NAME="queue_test";

        //获取连接
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //生命通道
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //定义消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);

        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
