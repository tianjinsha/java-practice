package com.grg.redis.jedis;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.TimeUnit;

/**
 * @author: tjshan
 * @date: 2020-04-12 15:55
 * FileName: PoolTest
 * Description:
 */
@Slf4j
public class PoolTest {

    private Jedis jedis =null;

    @Before
    public  void init(){
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        // 最大连接数
        poolConfig.setMaxTotal(GenericObjectPoolConfig.DEFAULT_MAX_TOTAL * 2);
        // 最大空闲连接数
        poolConfig.setMaxIdle(GenericObjectPoolConfig.DEFAULT_MAX_IDLE * 2);
        //最小空闲连接数
        poolConfig.setMinIdle(GenericObjectPoolConfig.DEFAULT_MIN_IDLE * 2);
        // 开启jmx功能
        poolConfig.setJmxEnabled(true);
        // 连接池没有连接后客户端的最大等待时间
        poolConfig.setMaxWaitMillis(3000);

        JedisPool jedisPool = new JedisPool(poolConfig, "192.168.43.87", 6379);
        try {
            jedis = jedisPool.getResource();
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }

    @Test
    public void setTest(){
        jedis.setnx("name","zhangsan");
        String name = jedis.get("name");
        log.info(name);
    }

    @Test
    public void getTest() throws InterruptedException {
        for (int i =0;i<20;i++){
            TimeUnit.SECONDS.sleep(1);
            jedis.set("name","zhangsan"+i);
            String name = jedis.get("name");
            log.info(name);
        }
    }
}
