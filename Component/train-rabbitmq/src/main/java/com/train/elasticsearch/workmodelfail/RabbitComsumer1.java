package com.train.elasticsearch.workmodelfail;

import com.rabbitmq.client.*;
import com.train.elasticsearch.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author tjshan
 * @date 2019/7/14 21:30
 */
public class RabbitComsumer1 {
    private static final String QUEUE_NAME="work_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //消息持久化
        //对已经存在的队列，每次声明的参数必须一致
        boolean durable =true;
        channel.queueDeclare(QUEUE_NAME,durable,false,false,null);
        //保证每次只分发一个
        channel.basicQos(1);

        //定义消费者
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {

                System.out.println("receive message : " + new String(body));
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //手动回复消息
                    channel.basicAck(envelope.getDeliveryTag(),false);
                    System.out.println("[1] done");
                }
            }
        };
        //自动应答 -false
        channel.basicConsume(QUEUE_NAME,false,consumer);
    }

}
