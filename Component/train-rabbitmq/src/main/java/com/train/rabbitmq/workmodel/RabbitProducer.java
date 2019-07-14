package com.train.rabbitmq.workmodel;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.train.rabbitmq.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 轮询模式
 * @author tjshan
 * @date 2019/7/14 21:12
 */
public class RabbitProducer {

    private static final String QUEUE_NAME="work_queue";
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        for (int i=0;i<50 ;i++){
            String message="Hello word "+i;
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            System.out.println("produce make message ["+message+"]");
            TimeUnit.SECONDS.sleep(2);
        }
        channel.close();
        connection.close();
    }
}
