package com.chengshi.train.method_5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-05 22:10
 */
@Component
public class JedisUtil_6 {

    @Autowired
    private JedisPool jedisPool;

    private Jedis getResource(){
        return jedisPool.getResource();
    }
    public byte[] set(byte[] key, byte[] value) {
        Jedis jedis=getResource();
        try {
            jedis.set(key,value);
            return value;
        }finally {
            jedis.close();
        }
    }

    public void expire(byte[] key, int i) {
        Jedis jedis=getResource();
        try {
            jedis.expire(key,i);
        } finally {
            jedis.close();
        }
    }

    public byte[] get(byte[] key) {
        Jedis jedis=getResource();
        try {
            byte[] value=jedis.get(key);
            return value;
        }finally {
            jedis.close();
        }
    }

    public void del(byte[] key) {
        Jedis jedis=getResource();
        try {
            jedis.del(key);

        }finally {
            jedis.close();
        }
    }

    public Set<byte[]> keys(String prefix) {
        Jedis jedis=getResource();
        try {
            return jedis.keys((prefix+"*").getBytes());
        }finally {
            jedis.close();
        }
    }
}
