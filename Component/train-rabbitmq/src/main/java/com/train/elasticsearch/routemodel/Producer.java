package com.train.elasticsearch.routemodel;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.train.elasticsearch.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author tjshan
 * @date 2019/7/15 21:42
 */
public class Producer {

    private static final String EXCHANGE_NAME="exchange_direct";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");

        for (int i=0;i<30;i++){
            String msg="hello direct "+i;
            String routeKey="error";
            System.out.println("send "+msg);
            TimeUnit.MILLISECONDS.sleep(500);
            channel.basicPublish(EXCHANGE_NAME,routeKey,null,msg.getBytes());
        }

        channel.close();
        connection.close();
    }
}
