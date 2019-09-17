package com.train.elasticsearch.subscribemodel;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.train.elasticsearch.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author tjshan
 * @date 2019/7/15 21:01
 */
public class Producer {

    private static final String EXCHANGE_NAME="subscribe-exchange";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");

        //发送消息
        for (int i=0;i<100;i++){
            String message="hello subscribe:"+i;
            channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes());
            System.out.println("send:" +message);
            TimeUnit.MILLISECONDS.sleep(200);
        }

        channel.close();
        connection.close();

    }
}
