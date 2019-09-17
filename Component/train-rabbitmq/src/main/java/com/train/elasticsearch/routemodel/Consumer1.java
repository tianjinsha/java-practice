package com.train.elasticsearch.routemodel;

import com.rabbitmq.client.*;
import com.train.elasticsearch.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author tjshan
 * @date 2019/7/15 21:53
 */
public class Consumer1 {

    private static final String EXCHANGE_NAME="exchange_direct";
    private static final String QUEUE_NAME="queue_direct_1";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

//        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue,EXCHANGE_NAME,"error");
        channel.basicQos(1);

        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                String msg=new String(body);
                System.out.println("[1] receive "+msg);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("[1] done");
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            }
        };
        channel.basicConsume(QUEUE_NAME,false,consumer);
    }
}
