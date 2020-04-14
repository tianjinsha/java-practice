package com.grg.redis.jedis.utils;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: tjshan
 * @date: 2020-04-12 14:49
 * FileName: ProtostuffUtils
 * Description:  Protostuff 序列化工具类
 */
public class ProtostuffUtils {

    /**
     * 避免每次序列化搜重新申请Buffer空间
     */
    private static LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);

    /**
     * 缓存Schema
     */
    private static Map<Class<?>,Schema<?>> schemaCache = new ConcurrentHashMap<>();


    /**
     * 序列化方法，将指定对象序列化为对象数组
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> byte[] serialize(T obj){
        Class<T> clazz = (Class<T>) obj.getClass();
        Schema<T> schema = getSchema(clazz);
        byte[] data;
        try {
            data = ProtostuffIOUtil.toByteArray(obj,schema,buffer);
        } finally {
            buffer.clear();
        }
        return  data;
    }

    /**
     * 反序列化方法，将字节数组反序列化为指定class类型
     * @param data
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T deserialize(byte[] data,Class<T> clazz){
        Schema<T> schema = getSchema(clazz);
        T obj = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(data,obj,schema);
        return obj;
    }

    private static <T> Schema<T> getSchema(Class<T> clazz){
        Schema<T> schema = (Schema<T>) schemaCache.get(clazz);

        if (Objects.isNull(schema)){
            schema = RuntimeSchema.getSchema(clazz);
            if (Objects.nonNull(schema)){
                schemaCache.put(clazz,schema);
            }
        }
        return  schema;
    }
 }
