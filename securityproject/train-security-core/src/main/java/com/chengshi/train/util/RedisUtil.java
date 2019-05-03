package com.chengshi.train.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RedisUtil {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;


    public boolean set(String key, String value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                redisTemplate.opsForValue().set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置key的生命周期，单位:s
     *
     * @param key
     * @param time
     */
    public void expireKey(String key, long time) {
        redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    public void del(String key) {
        redisTemplate.delete(key);
    }

    public Object get(String key) {
        log.info("[redisTemplate redis]取出 缓存  url:{} ", key);
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }
}
