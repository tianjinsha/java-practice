package com.train.rabbitmq.simplemodel;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.train.rabbitmq.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author tjshan
 * @since 2019/7/9 10:14
 */
public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        final String QUEUE_NAME="simple_queue";

        //获取连接
        Connection connection = ConnectionUtil.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //消息内容
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        String message="Hello Word3";

        channel.basicPublish("",QUEUE_NAME,null,message.getBytes());

        //关闭连接和通道
        channel.close();
        connection.close();
    }
}
