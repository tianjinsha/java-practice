package com.train.elasticsearch.transaction;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.train.elasticsearch.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author tjshan
 * @date 2019/7/16 20:03
 */
public class TxProducer {

    private static final String QUEUE_NAME="transaction";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        String msg="hello transaction";

        try {
            channel.txSelect();
            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
            channel.txCommit();
        } catch (Exception e) {
            channel.txRollback();
            System.out.println("send message rollback");
        }

        channel.close();
        connection.close();
    }
}
