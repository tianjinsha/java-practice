package com.train.rabbitmq.hellomodel;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import com.train.rabbitmq.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author tjshan
 * @since 2019/7/12 13:22
 */
public class RabbitProducer {

    private static final String EXCHANGE_NAME="exchange_demo";
    private static final String ROUTING_KEY="routingey_demo";
    private static final String QUEUE_NAME="simple_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接
        Connection connection = ConnectionUtil.getConnection();
        //创建信道
        Channel channel = connection.createChannel();
        //创建一个type='direct'、持久化、非自动删除的交换器
        channel.exchangeDeclare(EXCHANGE_NAME,"direct",true,false,null);
        //创建一个持久化、非排他、非自动删除的队列
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        //将交换器余队列通过路由建绑定
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,ROUTING_KEY);
        //发送一条持久化的消息
        String message="simple rabbitmq 哈哈哈 !!!";
        channel.basicPublish(EXCHANGE_NAME,ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());

        System.out.println("send a message : ["+message+"]");
        channel.close();
        connection.close();
    }

}
