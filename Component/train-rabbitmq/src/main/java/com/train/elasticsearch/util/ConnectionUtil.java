package com.train.elasticsearch.util;

import com.rabbitmq.client.Address;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 获取连接
 * @author tjshan
 * @since 2019/7/9 10:07
 */
public class ConnectionUtil {


    private static final String IP_ADDRESS="localhost";
    private static final String VIRTUAL_HOST ="/vhost";
    private static final int PORT=5672;

    public static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Address[] addresses=new Address[]{new Address(IP_ADDRESS,PORT)};
        factory.setHost(IP_ADDRESS);
        factory.setPort(PORT);
        factory.setVirtualHost(VIRTUAL_HOST);
        factory.setUsername("admin");
        factory.setPassword("admin");
//        Connection connection = factory.newConnection();
        Connection connection = factory.newConnection(addresses);

        return connection;
    }
}
