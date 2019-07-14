package com.chengshi.shiro.cache;

import com.chengshi.shiro.util.JedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-06 13:47
 */
@Slf4j
@Component
public class RedisCache<K, V> implements Cache<K, V> {
    private final String CACHE_PREFIX = "chengshi-cache:";

    @Autowired
    private JedisUtil jedisUtil;

    public String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private byte[] getKey(K k) {
//        SimplePrincipalCollection
        if (k instanceof String) {
            return (CACHE_PREFIX +name+"_"+ k).getBytes();
        }
        return SerializationUtils.serialize(k);
    }

    @Override
    public V get(K k) throws CacheException {
//        SimplePrincipalCollection simplePrincipalCollection;
        log.info("从缓存中获取授权信息");
        byte[] value = jedisUtil.get(getKey(k));
        if (value != null) {
            return (V) SerializationUtils.deserialize(value);
        }
        return null;
    }

    @Override
    public V put(K k, V v) throws CacheException {
        if (k instanceof PrincipalCollection){
            PrincipalCollection principalCollection= (PrincipalCollection) k;
            log.info(principalCollection.getRealmNames().toString());
            log.info(principalCollection.getRealmNames().toString());
        }
        PrincipalCollection principalCollection;
        byte[] key = getKey(k);
        if (k instanceof  Map){
            log.info("keys:: is MaP");
        }

        log.info("keys::::"+new String(key));

        byte[] value = SerializationUtils.serialize(v);
        jedisUtil.set(key, value);
        jedisUtil.expire(key, 600);
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        byte[] key = getKey(k);
        byte[] value = jedisUtil.get(key);
        if (value != null) {
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
