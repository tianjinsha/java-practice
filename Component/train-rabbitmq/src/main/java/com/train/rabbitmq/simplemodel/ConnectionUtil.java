package com.train.rabbitmq.simplemodel;

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

    public  static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory factory=new ConnectionFactory();
        factory .setHost("localhost");
        factory.setPort(5672);

        factory.setVirtualHost("/");
        factory.setUsername("admin");
        factory.setPassword("admin");

        Connection connection = factory.newConnection();
        return connection;
    }
}
