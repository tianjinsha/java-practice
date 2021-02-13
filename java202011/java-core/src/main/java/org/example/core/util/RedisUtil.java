package org.example.core.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author tjstj
 * @description TODO
 * @date 2021/2/9 16:35
 */
@Component
public class RedisUtil {

    @Qualifier("objectRedisTemplate")
    @Autowired
    private RedisTemplate<String, Object> objectRedisTemplate;

    /** -------------------key相关操作--------------------- */

    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    public boolean existsKey(String key) {
        return objectRedisTemplate.hasKey(key);
    }


    /**
     * 重命名key，如果newKey已经存在，则newKey的原值被覆盖
     *
     * @param oldKey
     * @param newKey
     */
    public void renameKey(String oldKey, String newKey) {
        objectRedisTemplate.rename(oldKey, newKey);
    }

    /**
     * 重命名key，newKey不存在时CIA重命名
     *
     * @param oldKey
     * @param newKey
     * @return
     */
    public boolean renameKeyExist(String oldKey, String newKey) {
        return objectRedisTemplate.renameIfAbsent(oldKey, newKey);
    }

    /**
     * 删除key
     *
     * @param key
     */
    public void deleteKey(String key) {
        objectRedisTemplate.delete(key);
    }

    /**
     * 删除多个key
     *
     * @param keys
     */
    public void deleteKey(String... keys) {
        Set<String> kSet = Stream.of(keys).map(k -> k).collect(Collectors.toSet());
        objectRedisTemplate.delete(kSet);
    }

    /**
     * 删除key的集合
     *
     * @param keys
     */
    public void delete(Collection<String> keys) {
        Set<String> kSet = keys.stream().map(k -> k).collect(Collectors.toSet());
        objectRedisTemplate.delete(kSet);
    }

    /**
     * 设置key的生命周期
     *
     * @param key
     * @param time
     * @param timeUnit
     */
    public void expireKey(String key, long time, TimeUnit timeUnit) {
        objectRedisTemplate.expire(key, time, timeUnit);
    }

    /**
     * 设置key的生命周期，单位:s
     *
     * @param key
     * @param time
     */
    public void expireKey(String key, long time) {
        objectRedisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    /**
     * 指定key在指定的时间过期
     *
     * @param key
     * @param date
     */
    public void expireKey(String key, Date date) {
        objectRedisTemplate.expireAt(key, date);
    }


    /**
     * 查询key的生命周期
     *
     * @param key
     * @param timeUnit
     * @return
     */
    public long getKeyExpire(String key, TimeUnit timeUnit) {
        return objectRedisTemplate.getExpire(key, timeUnit);
    }

    /**
     * 将key设置为永久有效
     *
     * @param key
     */
    public void persistKey(String key) {
        objectRedisTemplate.persist(key);
    }

    /**
     * 获取普通缓存
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        return key == null ? null : objectRedisTemplate.opsForValue().get(key);
    }

    /**
     * 放入普通缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, Object value) {
        try {
            objectRedisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 放入普通缓存并设置时间
     *
     * @param key
     * @param value
     * @param time
     * @return
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                objectRedisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                objectRedisTemplate.opsForValue().set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 递增
     *
     * @param key 键
     * @param by  要增加几(大于0)
     * @return
     */
    public long incr(String key, long by) {
        if (by < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return objectRedisTemplate.opsForValue().increment(key, by);
    }

    /**
     * 递减
     *
     * @param key 键
     * @param by  要减少几(小于0)
     * @return
     */
    public long decr(String key, long by) {
        if (by < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return objectRedisTemplate.opsForValue().increment(key, -by);
    }

    //===============================map=================================

    /**
     * HashGet
     *
     * @param key  不能为nll
     * @param item 不能为null
     * @return
     */
    public Object hget(String key, String item) {
        return objectRedisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hashkey对应的所有键值
     *
     * @param key
     * @return 对应的过个键值
     */
    public Map<Object, Object> hmget(String key) {
        return objectRedisTemplate.opsForHash().entries(key);
    }

    /**
     * 向一张hash表中放入数据，如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return
     */
    public boolean hset(String key, String item, Object value) {
        try {
            objectRedisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * HashSet
     *
     * @param key
     * @param map 对应的过个键值
     * @return
     */
    public boolean hmset(String key, Map<String, Object> map) {
        try {
            objectRedisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * HashSet 并设置时间
     *
     * @param key  键
     * @param map  对应多个键值
     * @param time 失效时间（s）
     * @return
     */
    public boolean hmset(String key, Map<String, Object> map, long time) {
        try {
            objectRedisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expireKey(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除hash表中的值
     *
     * @param key
     * @param item
     */
    public void hdel(String key, Object... item) {
        objectRedisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表中是否有改该项的值
     *
     * @param key  不能为null
     * @param item 不能为null
     * @return
     */
    public boolean hHashKeyExist(String key, String item) {
        return objectRedisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * hash递增，如果不存在，就会创建一个兵把新增后的值返回
     *
     * @param key
     * @param item
     * @param by   大于0
     * @return
     */
    public double hincr(String key, String item, double by) {
        return objectRedisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * hash递减
     *
     * @param key
     * @param item
     * @param by   小于0
     * @return
     */
    public double hdecr(String key, String item, double by) {
        return objectRedisTemplate.opsForHash().increment(key, item, -by);
    }

    //============================set=============================

    /**
     * *根据key获取Set中的所有值
     * *@paramkey 键
     * *@return
     *
     */
    public Set<Object> sGet(String key) {
        try {
            return objectRedisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * *根据value从一个set中查询,是否存在
     * *@param key键
     * *@param value值
     * *@return true存在false不存在
     *
     */


    public boolean sHasKey(String key, Object value) {
        try {
            return objectRedisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * *将数据放入set缓存
     * *@param key键
     * *@param values值可以是多个
     * *@return 成功个数
     *
     */


    public long sSet(String key, Object... values) {
        try {
            return objectRedisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * *将set数据放入缓存
     * *@param key键
     * *@param time时间(秒)
     * *@param values值可以是多个
     * *@return 成功个数
     *
     */


    public long sSetAndTime(String key, long time, Object... values) {
        try {
            Long count = objectRedisTemplate.opsForSet().add(key, values);
            if (time > 0) {
                expireKey(key, time);
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * *获取set缓存的长度
     * *@param key键
     * *@return
     *
     */


    public long sGetSetSize(String key) {
        try {
            return objectRedisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * *移除值为value的
     * *@param key键
     * *@param values值可以是多个
     * *@return 移除的个数
     *
     */


    public long setRemove(String key, Object... values) {
        try {
            Long count = objectRedisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    //===============================list=================================

    /**
     * *获取list缓存的内容
     * *@param key键
     * *@param start开始
     * *@param end结束0到-1代表所有值
     * *@return
     *
     */
    public List<Object> lGet(String key, long start, long end) {
        try {
            return objectRedisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * *获取list缓存的长度
     * *@param key键
     * *@return
     *
     */
    public long lGetListSize(String key) {
        try {
            return objectRedisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * *通过索引获取list中的值
     * *@param key键
     * *@param index索引index>=0时，0表头，1第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * *@return
     *
     */
    public Object lGetIndex(String key, long index) {
        try {
            return objectRedisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * *将list放入缓存
     * *@param key键
     * *@param value值
     * *@param time时间(秒)
     * *@return
     *
     */
    public boolean lSet(String key, Object value) {
        try {
            objectRedisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * *将list放入缓存
     * *@param key键
     * *@param value值
     * *@param time时间(秒)
     * *@return
     *
     */
    public boolean lSet(String key, Object value, long time) {
        try {
            objectRedisTemplate.opsForList().rightPush(key, value);
            if (time > 0) {
                expireKey(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * *将list放入缓存
     * *@param key键
     * *@param value值
     * *@param time时间(秒)
     * *@return
     *
     */
    public boolean lSet(String key, List<Object> value) {
        try {
            objectRedisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * *将list放入缓存
     * *@param key键
     * *@param value值
     * *@param time时间(秒)
     * *@return
     *
     */
    public boolean lSet(String key, List<Object> value, long time) {
        try {
            objectRedisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) {
                expireKey(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * *根据索引修改list中的某条数据
     * *@param key键
     * *@param index索引
     * *@param value值
     * *@return
     *
     */
    public boolean lUpdateIndex(String key, long index, Object value) {
        try {
            objectRedisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * *移除N个值为value
     * *@param key 键
     * *@param count 移除多少个
     * *@param value值
     * *@return 移除的个数
     *
     */
    public long lRemove(String key, long count, Object value) {
        try {
            Long remove = objectRedisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}

