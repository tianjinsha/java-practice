package org.example.core.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * @author tjstj
 * @description TODO
 * @date 2021/2/9 20:48
 */
@Configuration
public class RedisConfig {

    @Autowired
    JedisConnectionFactory  jedisConnectionFactory;

    @Bean(name="stringRedisTemplate")
    public RedisTemplate<String,String> stringStringRedisTemplate(){
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(jedisConnectionFactory);
        setSerializer(template);
        template.afterPropertiesSet();
        return  template;
    }

    @Bean(name="serializableRedisTemplate")
    public RedisTemplate<Serializable,Object> serializableRedisTemplate(){
        RedisTemplate<Serializable,Object>  template= new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory);
        setSerializer(template);
        template.afterPropertiesSet();
        return  template;
    }

    @Bean(name="objectRedisTemplate")
    public RedisTemplate<String,Object> objectRedisTemplate(){
        RedisTemplate<String,Object>  template= new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory);
        setSerializer(template);
        template.afterPropertiesSet();
        return  template;
    }

    private void setSerializer(RedisTemplate<? extends Serializable,?extends Object> template) {
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        template.setKeySerializer(stringRedisSerializer);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
    }
}

