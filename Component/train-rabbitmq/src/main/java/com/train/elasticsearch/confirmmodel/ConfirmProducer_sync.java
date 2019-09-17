package com.train.elasticsearch.confirmmodel;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.train.elasticsearch.util.ConnectionUtil;

import java.io.IOException;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author tjshan
 * @date 2019/7/16 21:13
 */
public class ConfirmProducer_sync {

    private static final String QUEUE_NAME="confirm_queue_sync";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        channel.confirmSelect();

        final SortedSet<Long> confirmSet = Collections.synchronizedSortedSet(new TreeSet<Long>());
        channel.addConfirmListener(new ConfirmListener() {
            //没有问题的handleAck
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                if (multiple){
                    System.out.println("handleAck----------------- multiple");
                    confirmSet.headSet(deliveryTag).clear();
                }else{
                    System.out.println("handleAck----------------- multiple false");
                    confirmSet.remove(deliveryTag);
                }
            }

            //失败的情况
            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                if (multiple){
                    System.out.println("handleNack-------------------multiple");
                    confirmSet.headSet(deliveryTag).clear();
                }else{
                    System.out.println("handleNack-------------------multiple false");
                    confirmSet.remove(deliveryTag);
                }
            }
        });


        int i=0;
        while(true){
            String msgString ="confirm sync"+i;
            long seqNo = channel.getNextPublishSeqNo();
            channel.basicPublish("",QUEUE_NAME,null,msgString.getBytes());
            confirmSet.add(seqNo);
            TimeUnit.MILLISECONDS.sleep(500);
        }
    }
}
