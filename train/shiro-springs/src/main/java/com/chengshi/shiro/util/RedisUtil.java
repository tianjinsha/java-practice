package com.chengshi.shiro.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-06 21:15
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<Serializable, Object> redisTemplate;
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    public Set<Serializable> keys() {
        return redisTemplate.keys("*");
    }

    public Object get(final String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate
                .opsForValue();
        result = operations.get(key);
        return result;
    }

    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate
                    .opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate
                    .opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
