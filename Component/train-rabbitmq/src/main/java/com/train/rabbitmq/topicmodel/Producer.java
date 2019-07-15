package com.train.rabbitmq.topicmodel;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.train.rabbitmq.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author tjshan
 * @date 2019/7/15 21:42
 */
public class Producer {

    private static final String EXCHANGE_NAME="exchange_topic";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"topic");

        for (int i=0;i<30;i++){
            String msg="hello topic "+i;
            String routeKey="goods.add";
            System.out.println("send "+msg);
            TimeUnit.MILLISECONDS.sleep(500);
            channel.basicPublish(EXCHANGE_NAME,routeKey,null,msg.getBytes());
        }

        channel.close();
        connection.close();
    }
}
