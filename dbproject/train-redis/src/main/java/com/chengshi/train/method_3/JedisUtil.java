package com.chengshi.train.method_3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.SortingParams;
import redis.clients.util.SafeEncoder;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class JedisUtil {

    private final int EXPIRE = 6000;
    @Autowired
    private JedisPool jedisPool;

    public Jedis getJedis() {
        if (jedisPool == null) {
            throw new NullPointerException();
        }
        return jedisPool.getResource();
    }

    public void closeJedis(Jedis jedis) {
        if (null != jedis && null != jedisPool) {
            jedis.close();
            jedisPool.close();
        }
    }

    /*************************key部分*******************************/

    /**
     * 更新key
     *
     * @param oldkey
     * @param newkey
     * @return
     */
    public String rename(String oldkey, String newkey) {
        Jedis jedis = getJedis();
        String status = jedis.rename(oldkey, newkey);
        closeJedis(jedis);
        return status;
    }

    /**
     * 更新key
     *
     * @param oldkey
     * @param newkey
     * @return
     */
    public String rename(byte[] oldkey, byte[] newkey) {
        Jedis jedis = getJedis();
        String status = jedis.rename(oldkey, newkey);
        closeJedis(jedis);
        return status;
    }


    /**
     * 跟新key,仅当key不存在时才执行
     *
     * @param oldkey
     * @param newKey
     * @return
     */
    public long renamenx(String oldkey, String newKey) {
        Jedis jedis = getJedis();
        long status = jedis.renamenx(oldkey, newKey);
        closeJedis(jedis);
        return status;
    }

    /**
     * 设置过期时间
     *
     * @param key
     * @param seconds
     */
    public void expire(String key, int seconds) {
        if (seconds < 0)
            return;
        Jedis jedis = getJedis();
        jedis.expire(key, seconds);
        closeJedis(jedis);
    }

    /**
     * 设置默认过期时间
     *
     * @param key
     */
    public void expire(String key) {
        expire(key, EXPIRE);
    }

    /**
     * 设置key的过期时间
     *
     * @param key
     * @param seconds
     * @return 返回影响的行数
     */
    public long expired(String key, int seconds) {
        Jedis jedis = getJedis();
        long count = jedis.expire(key, seconds);
        closeJedis(jedis);
        return count;
    }

    /**
     * 设置key的过期时间
     *
     * @param key
     * @param timestamp
     * @return
     */
    public long expireAt(String key, long timestamp) {
        Jedis jedis = getJedis();
        long count = jedis.expireAt(key, timestamp);
        closeJedis(jedis);
        return count;
    }

    /**
     * 查询key的过期时间
     *
     * @param key
     * @return
     */
    public long getKeyExpire(String key) {
        Jedis jedis = getJedis();
        long len = jedis.ttl(key);
        closeJedis(jedis);
        return len;
    }

    /**
     * 设置key永远不过期
     *
     * @param key
     * @return 当移除成功时返回1，key不存在或者移除不成功时返回0
     */
    public long persist(String key) {
        Jedis jedis = getJedis();
        long count = jedis.persist(key);
                

        return count;
    }

    /**
     * 删除keys对应的记录
     *
     * @param keys 键
     * @return 删除的数量
     */
    public long del(String... keys) {
        Jedis jedis = getJedis();
        Long count = jedis.del(keys);
        return count;
    }

    /**
     * 删除keys对应的记录
     *
     * @param keys
     * @return
     */
    public long del(byte[]... keys) {
        Jedis jedis = getJedis();
        long count = jedis.del(keys);
        closeJedis(jedis);
        return count;
    }


    /**
     * 清空所以key
     *
     * @return
     */
    public String flushAll() {
        Jedis jedis = getJedis();
        String stata = jedis.flushAll();
        return stata;
    }


    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    public boolean exists(String key) {
        Jedis jedis = getJedis();
        boolean exists = jedis.exists(key);
        closeJedis(jedis);
        return exists;
    }


    /**
     * 对List，set，SortSet 进行排序，如果集合数据较大应避免使用这个方法
     *
     * @param key
     * @return 返回排序后的结果，默认升序 sort key Desc为降序
     */
    public List<String> sort(String key) {
        Jedis jedis = getJedis();
        List<String> list = jedis.sort(key);

        closeJedis(jedis);
        return list;
    }


    /**
     * 对List，set，SortSet 进行排序，如果集合数据较大应避免使用这个方法
     *
     * @param key    键
     * @param parame
     * @return 返回排序后的结果，默认升序 sort key Desc为降序
     */
    public List<String> sort(String key, SortingParams parame) {
        Jedis jedis = getJedis();
        List<String> list = jedis.sort(key, parame);
        closeJedis(jedis);
        return list;
    }


    /**
     * 返回指定key的存储类型
     *
     * @param key
     * @return
     */
    public String type(String key) {
        Jedis jedis = getJedis();
        String type = jedis.type(key);
        closeJedis(jedis);
        return type;
    }

    /**
     * 查找所有匹配模式的键
     * key的查询表达式 *代表任意多个 ？代表一个
     *
     * @param pattern
     * @return
     */
    public Set<String> Keys(String pattern) {
        Jedis jedis = getJedis();
        Set<String> set = jedis.keys(pattern);

        closeJedis(jedis);
        return set;
    }

    /*************************set部分*******************************/

    /**
     * @param key
     * @param member
     * @return 如果member已经存在则返回0，否则返回1
     */
    public long sadd(String key, String member) {
        Jedis jedis = getJedis();

        Long s = jedis.sadd(key, member);
        closeJedis(jedis);
        return s;
    }

    /**
     * 向set添加一条记录
     *
     * @param key
     * @param member
     * @return
     */
    public long sadd(byte[] key, byte[] member) {
        Jedis jedis = getJedis();
        Long s = jedis.sadd(key, member);
        closeJedis(jedis);
        return s;
    }

    /**
     * 获取给定key中元素个数
     *
     * @param key
     * @return 元素个数
     */
    public long scard(String key) {
        Jedis jedis = getJedis();
        Long count = jedis.scard(key);
        closeJedis(jedis);
        return count;
    }


    /**
     * 返回从第一组和所有的给定集合之间的有差异的成员
     *
     * @param keys
     * @return 有差异成员的集合
     */
    public Set<String> sdiff(String... keys) {
        Jedis jedis = getJedis();
        Set<String> set = jedis.sdiff(keys);
        closeJedis(jedis);
        return set;
    }


    /**
     * 这个命令的作用和 SDIFF 类似，但它将结果保存到 destination 集合，而不是简单地返回结果集,如果目标已存在，则覆盖
     *
     * @param newkey
     * @param keys
     * @return 新集合的记录数
     */
    public long sdiffstore(String newkey, String... keys) {
        Jedis jedis = getJedis();
        Long count = jedis.sdiffstore(newkey, keys);
        closeJedis(jedis);
        return count;
    }


    /**
     * 返回给定集合交集成员，如果其中一个集合为不存在或为空，则返回空set
     *
     * @param keys
     * @return 交集成员的集合
     */
    public Set<String> sinter(String... keys) {
        Jedis jedis = getJedis();
        Set<String> set = jedis.sinter(keys);
        closeJedis(jedis);
        return set;
    }

    /**
     * sinterstore 这个命令类似于 SINTER 命令，但它将结果保存到 destination 集合，而不是简单地返回结果集。
     * 如果 destination 集合已经存在，则将其覆盖。destination 可以是 key 本身
     *
     * @param dstkey
     * @param keys
     * @return 新集合的记录数
     */
    public long sinterstore(String dstkey, String... keys) {
        Jedis jedis = getJedis();
        long count = jedis.sinterstore(dstkey, keys);
        closeJedis(jedis);
        return count;
    }

    /**
     * 确定一个给定的值是否存在
     *
     * @param key    要判断的值
     * @param member
     * @return 存在返回1，不存在返回0
     */
    public boolean sismember(String key, String member) {
        Jedis jedis = getJedis();
        Boolean s = jedis.sismember(key, member);
        closeJedis(jedis);
        return s;
    }

    /**
     * 返回集合中的所有成员
     *
     * @param key
     * @return 成员集合
     */
    public Set<String> smembers(String key) {
        Jedis jedis = getJedis();
        Set<String> set = jedis.smembers(key);
        closeJedis(jedis);
        return set;
    }

    public Set<byte[]> smembers(byte[] key) {
        Jedis jedis = getJedis();
        Set<byte[]> set = jedis.smembers(key);
        closeJedis(jedis);
        return set;
    }


    /**
     * smove 将成员从源集合移除放入目标集合 </br>
     * 如果源集合不存在或不包含指定成员，不进行任何操作，返回0</br>
     * 否则该成员从源集合上删除，并添加到目标集合，如果目标集合成员以存在，则只在源集合进行删除
     *
     * @param srckey 源集合  dstkey目标集合   member源集合中的成员
     * @param dstkey
     * @param member
     * @return 状态码 1成功 0失败
     */
    public long smove(String srckey, String dstkey, String member) {
        Jedis jedis = getJedis();
        Long s = jedis.smove(srckey, dstkey, member);
        closeJedis(jedis);
        return s;
    }

    /**
     * 从集合中删除成员  移除并返回集合中的一个随机元素。
     *
     * @param key
     * @return 被删除的随机成员
     */
    public String spop(String key) {
        Jedis jedis = getJedis();
        String s = jedis.spop(key); //s 被移除的随机成员
        closeJedis(jedis);
        return s;
    }

    /**
     * 从集合中删除指定成员
     * 移除集合 key 中的一个或多个 member 元素，不存在的 member 元素会被忽略。当 key 不是集合类型，返回一个错误
     *
     * @param key    member要删除的成员
     * @param member
     * @return 状态码 成功返回1，成员不存在返回0
     */
    public long srem(String key, String member) {
        Jedis jedis = getJedis();
        Long s = jedis.srem(key, member);
        closeJedis(jedis);
        return s;
    }

    /**
     * 合并多个集合并将合并后的结果集保存在指定的新集合中，如果新集合已经存在则覆盖
     *
     * @param keys
     * @return
     */
    public Set<String> sunion(String... keys) {
        Jedis jedis = getJedis();
        Set<String> set = jedis.sunion(keys);
        closeJedis(jedis);
        return set;
    }

    /**
     * 这个命令类似于 SUNION 命令，但它将结果保存到 destination 集合，而不是简单地返回结果集。
     * 如果 destination 已经存在，则将其覆盖。  destination 可以是 key 本身
     *
     * @param dstkey
     * @param keys
     * @return
     */
    public long sunionstore(String dstkey, String... keys) {
        Jedis jedis = getJedis();
        Long s = jedis.sunionstore(dstkey, keys);
        closeJedis(jedis);
        return s;
    }


    /******************************SortSet******************************/

    /**
     * 向集合中增加一条记录，如果这个值已经存在，这个值对应的权重将被置为新的权重
     *
     * @param key
     * @param score  权重 member要加入的值
     * @param member
     * @return 状态码 1成功 0已经存在member值
     */
    public long zadd(String key, double score, String member) {
        Jedis jedis = getJedis();
        long s = jedis.zadd(key, score, member);
        closeJedis(jedis);
        return s;
    }

    /**
     * 获取集合中元素的数量
     *
     * @param key
     * @return 当 key 存在且是有序集类型时，返回有序集的基数。 当 key 不存在时，返回 0 。
     */
    public long zcard(String key) {
        Jedis jedis = getJedis();
        long count = jedis.zcard(key);
        closeJedis(jedis);
        return count;
    }

    /**
     * 获取指定权重区间内的集合数量
     *
     * @param key
     * @param min 最小排序位置
     * @param max 最大排序位置
     * @return
     */
    public long zcount(String key, double min, double max) {
        Jedis jedis = getJedis();
        long count = jedis.zcount(key, min, max);
        closeJedis(jedis);
        return count;
    }

    /**
     * 返回有序集合key中，指定区间的成员0，-1指的是整个区间的成员
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> zrange(String key, int start, int end) {

        Jedis jedis = getJedis();
        Set<String> set = jedis.zrange(key, 0, -1);
        closeJedis(jedis);
        return set;
    }


    /**
     * 返回有序集 key 中，指定区间内的成员。其中成员的位置按 score 值递减(从大到小)来排列
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> zrevrange(String key, int start, int end) {
        // ShardedJedis jedis = getShardedJedis();
        Jedis jedis = getJedis();
        Set<String> set = jedis.zrevrange(key, start, end);
        closeJedis(jedis);
        return set;
    }

    /**
     * 根据上下权重查询集合
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Set<String> zrangeByScore(String key, double min, double max) {
        Jedis jedis = getJedis();
        Set<String> set = jedis.zrangeByScore(key, min, max);
        closeJedis(jedis);
        return set;
    }

    /**
     * 接上面方法，获取有序集合长度
     *
     * @param key
     * @return
     */
    public long zlength(String key) {
        long len = 0;
        Set<String> set = zrange(key, 0, -1);
        len = set.size();
        return len;
    }

    /**
     * 为有序集 key 的成员 member 的 score 值加上增量 increment
     *
     * @param key
     * @param score
     * @param member
     * @return
     */
    public double zincrby(String key, double score, String member) {
        Jedis jedis = getJedis();
        double s = jedis.zincrby(key, score, member);
        closeJedis(jedis);
        return s;
    }

    /**
     * 返回有序集 key 中成员 member 的排名。其中有序集成员按 score 值递增(从小到大)顺序排列
     *
     * @param key
     * @param member
     * @return
     */
    public long zrank(String key, String member) {
        Jedis jedis = getJedis();
        long index = jedis.zrank(key, member);
        closeJedis(jedis);
        return index;
    }

    /**
     * 返回有序集 key 中成员 member 的排名。其中有序集成员按 score 值递减(从大到小)排序。
     *
     * @param key
     * @param member
     * @return
     */
    public long zrevrank(String key, String member) {
        Jedis jedis = getJedis();
        long index = jedis.zrevrank(key, member);
        closeJedis(jedis);
        return index;
    }

    /**
     * 移除有序集 key 中的一个或多个成员，不存在的成员将被忽略。
     * 当 key 存在但不是有序集类型时，返回一个错误。在 Redis 2.4 版本以前， ZREM 每次只能删除一个元素。
     *
     * @param key
     * @param member
     * @return 被成功移除的成员的数量，不包括被忽略的成员
     */
    public long zrem(String key, String member) {
        Jedis jedis = getJedis();
        long count = jedis.zrem(key, member);
        closeJedis(jedis);
        return count;

    }

    /**
     * 移除有序集 key 中，指定排名(rank)区间内的所有成员
     *
     * @param key
     * @param start
     * @param end
     * @return 被移除成员的数量
     */
    public long zremrangeByRank(String key, int start, int end) {
        Jedis jedis = getJedis();
        long count = jedis.zremrangeByRank(key, start, end);
        closeJedis(jedis);
        return count;

    }


    /**
     * 删除指定权重区间的元素
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    public long zremrangeByScore(String key, double min, double max) {
        Jedis jedis = getJedis();
        long count = jedis.zremrangeByScore(key, min, max);
        closeJedis(jedis);
        return count;
    }


    /**
     * 获取给定值在集合中的权重
     *
     * @param key
     * @param member
     * @return
     */
    public double zscore(String key, String member) {
        Jedis jedis = getJedis();
        Double score = jedis.zscore(key, member);
        closeJedis(jedis);
        if (score != null) {
            return score;
        }
        return 0;
    }


    /*******************************hash***********************************/

    /**
     * 从hash中删除指定的存储
     *
     * @param key
     * @param fieid 存储的名字
     * @return 状态码，1成功，0失败
     */
    public long hdel(String key, String fieid) {
        Jedis jedis = getJedis();
        long s = jedis.hdel(key, fieid);
        closeJedis(jedis);
        return s;
    }

    public long hdel(String key) {
        Jedis jedis = getJedis();
        long s = jedis.del(key);
        closeJedis(jedis);
        return s;
    }

    /**
     * 测试hash中指定的存储是否存在
     *
     * @param key
     * @param fieid 存储的名字
     * @return 1存在，0不存在
     */
    public boolean hexists(String key, String fieid) {
        // ShardedJedis jedis = getShardedJedis();
        Jedis jedis = getJedis();
        boolean s = jedis.hexists(key, fieid);
        closeJedis(jedis);
        return s;
    }

    /**
     * 返回hash中指定存储位置的值
     *
     * @param key
     * @param fieid 存储的名字
     * @return 存储对应的值
     */
    public String hget(String key, String fieid) {
        // ShardedJedis jedis = getShardedJedis();
        Jedis jedis = getJedis();
        String s = jedis.hget(key, fieid);
        closeJedis(jedis);
        return s;
    }

    public byte[] hget(byte[] key, byte[] fieid) {
        // ShardedJedis jedis = getShardedJedis();
        Jedis jedis = getJedis();
        byte[] s = jedis.hget(key, fieid);
        closeJedis(jedis);
        return s;
    }

    /**
     * 以Map的形式返回hash中的存储和值
     *
     * @param key
     * @return Map<Strinig   ,   String>
     */
    public Map<String, String> hgetAll(String key) {
        // ShardedJedis jedis = getShardedJedis();
        Jedis jedis = getJedis();
        Map<String, String> map = jedis.hgetAll(key);
        closeJedis(jedis);
        return map;
    }

    /**
     * 添加一个对应关系
     *
     * @param key
     * @param fieid
     * @param value
     * @return 状态码 1成功，0失败，fieid已存在将更新，也返回0
     **/
    public long hset(String key, String fieid, String value) {
        Jedis jedis = getJedis();
        long s = jedis.hset(key, fieid, value);
        closeJedis(jedis);
        return s;
    }

    public long hset(String key, String fieid, byte[] value) {
        Jedis jedis = getJedis();
        long s = jedis.hset(key.getBytes(), fieid.getBytes(), value);
        closeJedis(jedis);
        return s;
    }

    /**
     * 添加对应关系，只有在fieid不存在时才执行
     *
     * @param key
     * @param fieid
     * @param value
     * @return 状态码 1成功，0失败fieid已存
     **/
    public long hsetnx(String key, String fieid, String value) {
        Jedis jedis = getJedis();
        long s = jedis.hsetnx(key, fieid, value);
        closeJedis(jedis);
        return s;
    }

    /**
     * 获取hash中value的集合
     *
     * @param key
     * @return List<String>
     */
    public List<String> hvals(String key) {
        // ShardedJedis jedis = getShardedJedis();
        Jedis jedis = getJedis();
        List<String> list = jedis.hvals(key);
        closeJedis(jedis);
        return list;
    }

    /**
     * 在指定的存储位置加上指定的数字，存储位置的值必须可转为数字类型
     *
     * @param key
     * @param fieid 存储位置
     * @param value 要增加的值,可以是负数
     * @return 增加指定数字后，存储位置的值
     */
    public long hincrby(String key, String fieid, long value) {
        Jedis jedis = getJedis();
        long s = jedis.hincrBy(key, fieid, value);
        closeJedis(jedis);
        return s;
    }

    /**
     * 返回指定hash中的所有存储名字,类似Map中的keySet方法
     *
     * @param key
     * @return Set<String> 存储名称的集合
     */
    public Set<String> hkeys(String key) {
        // ShardedJedis jedis = getShardedJedis();
        Jedis jedis = getJedis();
        Set<String> set = jedis.hkeys(key);
        closeJedis(jedis);
        return set;
    }

    /**
     * 获取hash中存储的个数，类似Map中size方法
     *
     * @param key
     * @return long 存储的个数
     */
    public long hlen(String key) {
        // ShardedJedis jedis = getShardedJedis();
        Jedis jedis = getJedis();
        long len = jedis.hlen(key);
        closeJedis(jedis);
        return len;
    }

    /**
     * 根据多个key，获取对应的value，返回List,如果指定的key不存在,List对应位置为null
     *
     * @param key
     * @param fieids 存储位置
     * @return List<String>
     */
    public List<String> hmget(String key, String... fieids) {
        // ShardedJedis jedis = getShardedJedis();
        Jedis jedis = getJedis();
        List<String> list = jedis.hmget(key, fieids);
        closeJedis(jedis);
        return list;
    }

    public List<byte[]> hmget(byte[] key, byte[]... fieids) {
        // ShardedJedis jedis = getShardedJedis();
        Jedis jedis = getJedis();
        List<byte[]> list = jedis.hmget(key, fieids);
        closeJedis(jedis);
        return list;
    }

    /**
     * 添加对应关系，如果对应关系已存在，则覆盖
     *
     * @param key
     * @param map 对应关系
     * @return 状态，成功返回OK
     */
    public String hmset(String key, Map<String, String> map) {
        Jedis jedis = getJedis();
        String s = jedis.hmset(key, map);
        closeJedis(jedis);
        return s;
    }

    /**
     * 添加对应关系，如果对应关系已存在，则覆盖
     *
     * @param key
     * @param map 对应关系
     * @return 状态，成功返回OK
     */
    public String hmset(byte[] key, Map<byte[], byte[]> map) {
        Jedis jedis = getJedis();
        String s = jedis.hmset(key, map);
        closeJedis(jedis);
        return s;
    }


    // *******************************************Strings*******************************************//

    /**
     * 根据key获取记录
     *
     * @param key
     * @return 值
     */
    public String get(String key) {
        // ShardedJedis jedis = getShardedJedis();
        Jedis jedis = getJedis();
        String value = jedis.get(key);
        closeJedis(jedis);
        return value;
    }

    /**
     * 根据key获取记录
     *
     * @param key
     * @return 值
     */
    public byte[] get(byte[] key) {
        // ShardedJedis jedis = getShardedJedis();
        Jedis jedis = getJedis();
        byte[] value = jedis.get(key);
        closeJedis(jedis);
        return value;
    }

    /**
     * 添加有过期时间的记录
     *
     * @param key
     * @param seconds 过期时间，以秒为单位
     * @param value
     * @return String 操作状态
     */
    public String setEx(String key, int seconds, String value) {
        Jedis jedis = getJedis();
        String str = jedis.setex(key, seconds, value);
        closeJedis(jedis);
        return str;
    }

    /**
     * 添加有过期时间的记录
     *
     * @param key
     * @param seconds 过期时间，以秒为单位
     * @param value
     * @return String 操作状态
     */
    public String setEx(byte[] key, int seconds, byte[] value) {
        Jedis jedis = getJedis();
        String str = jedis.setex(key, seconds, value);
        closeJedis(jedis);
        return str;
    }

    /**
     * 添加一条记录，仅当给定的key不存在时才插入
     *
     * @param key
     * @param value
     * @return long 状态码，1插入成功且key不存在，0未插入，key存在
     */
    public long setnx(String key, String value) {
        Jedis jedis = getJedis();
        long str = jedis.setnx(key, value);
        closeJedis(jedis);
        return str;
    }

    /**
     * 添加记录,如果记录已存在将覆盖原有的value
     *
     * @param key
     * @param value
     * @return 状态码
     */
    public String set(String key, String value) {
        return set(SafeEncoder.encode(key), SafeEncoder.encode(value));
    }

    /**
     * 添加记录,如果记录已存在将覆盖原有的value
     *
     * @param key
     * @param value
     * @return 状态码
     */
    public String set(String key, byte[] value) {
        return set(SafeEncoder.encode(key), value);
    }

    /**
     * 添加记录,如果记录已存在将覆盖原有的value
     *
     * @param key
     * @param value
     * @return 状态码
     */
    public String set(byte[] key, byte[] value) {
        Jedis jedis = getJedis();
        String status = jedis.set(key, value);
        closeJedis(jedis);
        return status;
    }

    /**
     * 从指定位置开始插入数据，插入的数据会覆盖指定位置以后的数据<br/>
     * 例:String str1="123456789";<br/>
     * 对str1操作后setRange(key,4,0000)，str1="123400009";
     *
     * @param key
     * @param offset
     * @param value
     * @return long value的长度
     */
    public long setRange(String key, long offset, String value) {
        Jedis jedis = getJedis();
        long len = jedis.setrange(key, offset, value);
        closeJedis(jedis);
        return len;
    }

    /**
     * 在指定的key中追加value
     *
     * @param key
     * @param value
     * @return long 追加后value的长度
     **/
    public long append(String key, String value) {
        Jedis jedis = getJedis();
        long len = jedis.append(key, value);
        closeJedis(jedis);
        return len;
    }

    /**
     * 将key对应的value减去指定的值，只有value可以转为数字时该方法才可用
     *
     * @param key
     * @param number 要减去的值
     * @return long 减指定值后的值
     */
    public long decrBy(String key, long number) {
        Jedis jedis = getJedis();
        long len = jedis.decrBy(key, number);
        closeJedis(jedis);
        return len;
    }

    /**
     * <b>可以作为获取唯一id的方法</b><br/>
     * 将key对应的value加上指定的值，只有value可以转为数字时该方法才可用
     *
     * @param key
     * @param number 要减去的值
     * @return long 相加后的值
     */
    public long incrBy(String key, long number) {
        Jedis jedis = getJedis();
        long len = jedis.incrBy(key, number);
        closeJedis(jedis);
        return len;
    }

    /**
     * 对指定key对应的value进行截取
     *
     * @param key
     * @param startOffset 开始位置(包含)
     * @param endOffset   结束位置(包含)
     * @return String 截取的值
     */
    public String getrange(String key, long startOffset, long endOffset) {
        // ShardedJedis jedis = getShardedJedis();
        Jedis jedis = getJedis();
        String value = jedis.getrange(key, startOffset, endOffset);
        closeJedis(jedis);
        return value;
    }

    /**
     * 获取并设置指定key对应的value<br/>
     * 如果key存在返回之前的value,否则返回null
     *
     * @param key
     * @param value
     * @return String 原始value或null
     */
    public String getSet(String key, String value) {
        Jedis jedis = getJedis();
        String str = jedis.getSet(key, value);
        closeJedis(jedis);
        return str;
    }

    /**
     * 批量获取记录,如果指定的key不存在返回List的对应位置将是null
     *
     * @param keys
     * @return List<String> 值得集合
     */
    public List<String> mget(String... keys) {
        Jedis jedis = getJedis();
        List<String> str = jedis.mget(keys);
        closeJedis(jedis);
        return str;
    }

    /**
     * 批量存储记录
     *
     * @param keysvalues 例:keysvalues="key1","value1","key2","value2";
     * @return String 状态码
     */
    public String mset(String... keysvalues) {
        Jedis jedis = getJedis();
        String str = jedis.mset(keysvalues);
        closeJedis(jedis);
        return str;
    }

    /**
     * 获取key对应的值的长度
     *
     * @param key
     * @return value值得长度
     */
    public long strlen(String key) {
        Jedis jedis = getJedis();
        long len = jedis.strlen(key);
        closeJedis(jedis);
        return len;
    }

    // *******************************************Lists*******************************************//

    /**
     * List长度
     *
     * @param key
     * @return 长度
     */
    public long llen(String key) {
        return llen(SafeEncoder.encode(key));
    }

    /**
     * List长度
     *
     * @param key
     * @return 长度
     */
    public long llen(byte[] key) {
        // ShardedJedis jedis = getShardedJedis();
        Jedis jedis = getJedis();
        long count = jedis.llen(key);
        closeJedis(jedis);
        return count;
    }

    /**
     * 覆盖操作,将覆盖List中指定位置的值
     *
     * @param key
     * @param index 位置
     * @param value 值
     * @return 状态码
     */
    public String lset(byte[] key, int index, byte[] value) {
        Jedis jedis = getJedis();
        String status = jedis.lset(key, index, value);
        closeJedis(jedis);
        return status;
    }

    /**
     * 覆盖操作,将覆盖List中指定位置的值
     *
     * @param key
     * @param index 位置
     * @param value 值
     * @return 状态码
     */
    public String lset(String key, int index, String value) {
        return lset(SafeEncoder.encode(key), index, SafeEncoder.encode(value));
    }

    /**
     * 在value的相对位置插入记录
     *
     * @param key
     * @param where 前面插入或后面插入
     * @param pivot 相对位置的内容
     * @param value 插入的内容
     * @return 记录总数
     */
    public long linsert(String key, BinaryClient.LIST_POSITION where, String pivot,
                        String value) {
        return linsert(SafeEncoder.encode(key), where,
                SafeEncoder.encode(pivot), SafeEncoder.encode(value));
    }

    /**
     * 在指定位置插入记录
     *
     * @param key
     * @param where 前面插入或后面插入
     * @param pivot 相对位置的内容
     * @param value 插入的内容
     * @return 记录总数
     */
    public long linsert(byte[] key, BinaryClient.LIST_POSITION where, byte[] pivot,
                        byte[] value) {
        Jedis jedis = getJedis();
        long count = jedis.linsert(key, where, pivot, value);
        closeJedis(jedis);
        return count;
    }

    /**
     * 获取List中指定位置的值
     *
     * @param key
     * @param index 位置
     * @return 值
     **/
    public String lindex(String key, int index) {
        return SafeEncoder.encode(lindex(SafeEncoder.encode(key), index));
    }

    /**
     * 获取List中指定位置的值
     *
     * @param key
     * @param index 位置
     * @return 值
     **/
    public byte[] lindex(byte[] key, int index) {
        // ShardedJedis jedis = getShardedJedis();
        Jedis jedis = getJedis();
        byte[] value = jedis.lindex(key, index);
        closeJedis(jedis);
        return value;
    }

    /**
     * 将List中的第一条记录移出List
     *
     * @param key
     * @return 移出的记录
     */
    public String lpop(String key) {
        return SafeEncoder.encode(lpop(SafeEncoder.encode(key)));
    }

    /**
     * 将List中的第一条记录移出List
     *
     * @param key
     * @return 移出的记录
     */
    public byte[] lpop(byte[] key) {
        Jedis jedis = getJedis();
        byte[] value = jedis.lpop(key);
        closeJedis(jedis);
        return value;
    }

    /**
     * 将List中最后第一条记录移出List
     *
     * @param key
     * @return 移出的记录
     */
    public String rpop(String key) {
        Jedis jedis = getJedis();
        String value = jedis.rpop(key);
        closeJedis(jedis);
        return value;
    }

    /**
     * 向List尾部追加记录
     *
     * @param key
     * @param value
     * @return 记录总数
     */
    public long lpush(String key, String value) {
        return lpush(SafeEncoder.encode(key), SafeEncoder.encode(value));
    }

    /**
     * 向List头部追加记录
     *
     * @param key
     * @param value
     * @return 记录总数
     */
    public long rpush(String key, String value) {
        Jedis jedis = getJedis();
        long count = jedis.rpush(key, value);
        closeJedis(jedis);
        return count;
    }

    /**
     * 向List头部追加记录
     *
     * @param key
     * @param value
     * @return 记录总数
     */
    public long rpush(byte[] key, byte[] value) {
        Jedis jedis = getJedis();
        long count = jedis.rpush(key, value);
        closeJedis(jedis);
        return count;
    }

    /**
     * 向List中追加记录
     *
     * @param key
     * @param value
     * @return 记录总数
     */
    public long lpush(byte[] key, byte[] value) {
        Jedis jedis = getJedis();
        long count = jedis.lpush(key, value);
        closeJedis(jedis);
        return count;
    }

    /**
     * 获取指定范围的记录，可以做为分页使用
     *
     * @param key
     * @param start
     * @param end
     * @return List
     */
    public List<String> lrange(String key, long start, long end) {
        // ShardedJedis jedis = getShardedJedis();
        Jedis jedis = getJedis();
        List<String> list = jedis.lrange(key, start, end);
        closeJedis(jedis);
        return list;
    }

    /**
     * 获取指定范围的记录，可以做为分页使用
     *
     * @param key
     * @param start
     * @param end   如果为负数，则尾部开始计算
     * @return List
     */
    public List<byte[]> lrange(byte[] key, int start, int end) {
        // ShardedJedis jedis = getShardedJedis();
        Jedis jedis = getJedis();
        List<byte[]> list = jedis.lrange(key, start, end);
        closeJedis(jedis);
        return list;
    }

    /**
     * 删除List中c条记录，被删除的记录值为value
     *
     * @param key
     * @param c     要删除的数量，如果为负数则从List的尾部检查并删除符合的记录
     * @param value 要匹配的值
     * @return 删除后的List中的记录数
     */
    public long lrem(byte[] key, int c, byte[] value) {
        Jedis jedis = getJedis();
        long count = jedis.lrem(key, c, value);
        closeJedis(jedis);
        return count;
    }

    /**
     * 删除List中c条记录，被删除的记录值为value
     *
     * @param key
     * @param c     要删除的数量，如果为负数则从List的尾部检查并删除符合的记录
     * @param value 要匹配的值
     * @return 删除后的List中的记录数
     */
    public long lrem(String key, int c, String value) {
        return lrem(SafeEncoder.encode(key), c, SafeEncoder.encode(value));
    }

    /**
     * 算是删除吧，只保留start与end之间的记录
     *
     * @param key
     * @param start 记录的开始位置(0表示第一条记录)
     * @param end   记录的结束位置（如果为-1则表示最后一个，-2，-3以此类推）
     * @return 执行状态码
     */
    public String ltrim(byte[] key, int start, int end) {
        Jedis jedis = getJedis();
        String str = jedis.ltrim(key, start, end);
        closeJedis(jedis);
        return str;
    }

    /**
     * 算是删除吧，只保留start与end之间的记录
     *
     * @param key
     * @param start 记录的开始位置(0表示第一条记录)
     * @param end   记录的结束位置（如果为-1则表示最后一个，-2，-3以此类推）
     * @return 执行状态码
     */
    public String ltrim(String key, int start, int end) {
        return ltrim(SafeEncoder.encode(key), start, end);

    }

}
