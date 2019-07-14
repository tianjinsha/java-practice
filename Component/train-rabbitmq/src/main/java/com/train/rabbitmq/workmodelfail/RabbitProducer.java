package com.train.rabbitmq.workmodelfail;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.train.rabbitmq.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 公平分发模式
 * @author tjshan
 * @date 2019/7/14 21:12
 */
public class RabbitProducer {

    private static final String QUEUE_NAME="work_queue";
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        /**
         * 每个消费者发送确认消息之前，消息队列不会发送消息给消费者，一次只处理一个消息
         * 限制发送给同一个消费者不得超过一条
         */
        channel.basicQos(1);

        for (int i=0;i<50 ;i++){
            String message="Hello word "+i;
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            System.out.println("produce make message ["+message+"]");
            TimeUnit.MILLISECONDS.sleep(200);
        }
        channel.close();
        connection.close();
    }
}
