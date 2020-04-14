package com.grg.redis.jedis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @author: tjshan
 * @date: 2020-04-12 17:24
 * FileName: LuaTest
 * Description:
 */
@Slf4j
public class LuaTest {

    private Jedis jedis = null;

    @Before
    public void init(){
        try {
            jedis = new Jedis("192.168.43.87",6379);
        }catch (Exception e){
            log.error(e.getMessage());
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }

    @Test
    public void getTest(){
        String key = "hello";
        String script = "return redis.call('get',KEYS[1])";
        Object result = jedis.eval(script, 1, key);
        log.info(result.toString());
    }
}
