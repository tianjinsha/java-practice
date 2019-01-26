package com.chengshi.train.method_3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class JedisUtil {

    @Autowired
    private JedisPool jedisPool;

    public Jedis getJedis() {
        if (jedisPool == null) {
            throw new NullPointerException();
        }
        return jedisPool.getResource();
    }

    public void returenJedis(Jedis jedis) {
        if (null != jedis && null != jedisPool) {
            jedisPool.returnResource(jedis);
        }
    }


    /**
     * 跟新key
     * @param oldkey
     * @param newkey
     * @return
     */
    public String rename(String oldkey, String newkey) {
        Jedis jedis = getJedis();
        String status = jedis.rename(oldkey, newkey);
        return status;
    }

    /**
     * 跟新key,仅当key不存在时才执行
     * @param oldkey
     * @param newKey
     * @return
     */
    public long renamenx(String oldkey,String newKey){
        Jedis jedis=getJedis();
        long status=jedis.renamenx(oldkey,newKey);
        return status;
    }

    /**
     * 设置默认过期时间
     *
     * @param key
     */
    public void expire(String key) {
        expire(key);
    }


}
