package com.chengshi.train;

import com.chengshi.train.method_6.RedisUtil_6;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-26 15:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SerializableRedis {

    @Autowired
    RedisUtil_6 redisUtil_6;

    @Test
    public void Test1(){
        redisUtil_6.set("test","hello");
    }
}
