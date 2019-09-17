package com.train.elasticsearch.subscribemodel;

import com.rabbitmq.client.*;
import com.train.elasticsearch.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author tjshan
 * @date 2019/7/15 21:08
 */
public class Consumer2 {


    private static final String EXCHANGE_NAME="subscribe-exchange";
    private static final String QUEUE_NAME="subscribe-queue-2";

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //绑定交换机
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");
        channel.basicQos(3);

        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                String msg = new String(body);
                System.out.println("[2] receive " + msg);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("[2] done");
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };

        channel.basicConsume(QUEUE_NAME,false,consumer);
    }
}
