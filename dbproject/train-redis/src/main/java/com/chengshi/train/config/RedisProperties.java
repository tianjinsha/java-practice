package com.chengshi.train.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.connection.ClusterNodeResourceProvider;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "spring.redis.jedis")
public class RedisProperties {
    private String host = "localhost";

    private int port = 6379;

    private String password;

    private int timeout;

    private boolean ssl;

    private RedisPoolProperties pool;

    private ClusterNodeResourceProvider cluster;

}
