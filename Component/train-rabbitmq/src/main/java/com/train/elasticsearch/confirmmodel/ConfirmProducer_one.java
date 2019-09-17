package com.train.elasticsearch.confirmmodel;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.train.elasticsearch.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author tjshan
 * @date 2019/7/16 20:25
 */
public class ConfirmProducer_one {
    private static final String QUEUE_NAME = "confirm-queue-normal";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        channel.confirmSelect();

        String msg="hello confirm message normal";
        channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());

        if (!channel.waitForConfirms()){
            System.out.println("message send fail !");
        }else{
            System.out.println("message send success !");
        }

        channel.close();
        connection.close();
    }
}
