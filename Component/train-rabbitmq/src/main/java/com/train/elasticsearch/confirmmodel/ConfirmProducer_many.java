package com.train.elasticsearch.confirmmodel;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.train.elasticsearch.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author tjshan
 * @date 2019/7/16 20:25
 */
public class ConfirmProducer_many {
    private static final String QUEUE_NAME = "confirm-queue-normal";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        channel.confirmSelect();

        for (int i=0;i<50;i++){
            String msg="hello confirm message many"+i;
            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
            TimeUnit.MILLISECONDS.sleep(500);
            System.out.println("send " +msg);
        }

        if (!channel.waitForConfirms()){
            System.out.println("message send fail !");
        }else{
            System.out.println("message send success !");
        }

        channel.close();
        connection.close();
    }
}
