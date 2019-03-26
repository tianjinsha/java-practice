package com.chengshi.train;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.JedisPoolConfig;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class JredisTest {

    @Autowired
    JedisPoolConfig jedisPoolConfig;

    @Test
    public void jRedisPoolTest(){
        log.info("max-total:"+jedisPoolConfig.getMaxTotal());
        log.info("maxIdle:"+jedisPoolConfig.getMaxIdle());
        log.info("max-waitMillis"+jedisPoolConfig.getMaxWaitMillis());
    }
}
