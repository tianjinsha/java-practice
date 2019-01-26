package com.chengshi.train.method_6;

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
 * @date: 2019-01-26 15:46
 */
@Component
public class RedisUtil_6 {
    @Autowired
    private RedisTemplate<Serializable, Object> SerializableRedisTemplate;

    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    public void remove(final String key) {
        if (exists(key)) {
            SerializableRedisTemplate.delete(key);
        }
    }
    public boolean exists(final String key) {
        return SerializableRedisTemplate.hasKey(key);
    }

    public Set<Serializable> keys() {
        return SerializableRedisTemplate.keys("*");
    }

    public Object get(final String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = SerializableRedisTemplate
                .opsForValue();
        result = operations.get(key);
        return result;
    }

    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = SerializableRedisTemplate
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
            ValueOperations<Serializable, Object> operations = SerializableRedisTemplate
                    .opsForValue();
            operations.set(key, value);
            SerializableRedisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
