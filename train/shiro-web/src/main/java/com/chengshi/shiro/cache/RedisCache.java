package com.chengshi.shiro.cache;

import com.chengshi.shiro.util.JedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import java.util.Collection;
import java.util.Set;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-06 13:47
 */
@Slf4j
@Component
public class RedisCache<K,V> implements Cache<K,V>{
    private final String CACHE_PREFIX="chengshi-cache:";

    @Autowired
    private JedisUtil jedisUtil;

    private byte[] getKey(K k){
        if (k instanceof String){
            return (CACHE_PREFIX+k).getBytes();
        }
        return SerializationUtils.serialize(k);
    }

    @Override
    public V get(K k) throws CacheException {
        log.info("从缓存中获取授权信息");
        byte[] value=jedisUtil.get(getKey(k));
        if (value!=null){
            return (V) SerializationUtils.deserialize(value);
        }
        return null;
    }

    @Override
    public V put(K k, V v) throws CacheException {
        byte[] key=getKey(k);
        byte[] value=SerializationUtils.serialize(v);
        jedisUtil.set(key,value);
        jedisUtil.expire(key,600);
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        byte[] key=getKey(k);
        byte[] value=jedisUtil.get(key);
        if (value!=null){
            return (V) SerializationUtils.deserialize(value);
        }
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }
}
