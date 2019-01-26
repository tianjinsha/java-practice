package com.chengshi.train.method_6;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-26 15:49
 */
@Configuration
public class RedisConfig_6 {

    @Autowired
    JedisConnectionFactory jedisConnectionFactory;

    @Bean(name = "SerializableRedisTemplate")
    public RedisTemplate<Serializable,Object> objectRedisTemplate(){
        JedisConnectionFactory factory=jedisConnectionFactory;
        RedisTemplate<Serializable,Object> redisTemplate=new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        setSerializer(redisTemplate);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }


    private void setSerializer(RedisTemplate template) {
        RedisSerializer stringSerizlizer=new StringRedisSerializer();
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer=new GenericJackson2JsonRedisSerializer();
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//        om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setKeySerializer(stringSerizlizer);
        template.setHashKeySerializer(stringSerizlizer);

        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
//        template.setValueSerializer(genericJackson2JsonRedisSerializer);
//        template.setHashValueSerializer(genericJackson2JsonRedisSerializer);
    }
}
