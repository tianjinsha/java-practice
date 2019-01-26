package com.chengshi.train.method_3;

import com.chengshi.train.config.RedisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig_3 {

    @Autowired
    private RedisProperties redisProperties;
    @Autowired
    private JedisPoolConfig jedisPoolConfig;


    @Bean
    public JedisPool jedisPool(){
        JedisPool pool=new JedisPool(jedisPoolConfig,redisProperties.getHost(),redisProperties.getPort());
        return pool;
    }
}
