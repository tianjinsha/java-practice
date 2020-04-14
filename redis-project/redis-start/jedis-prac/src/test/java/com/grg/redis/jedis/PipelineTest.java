package com.grg.redis.jedis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.Arrays;
import java.util.List;

/**
 * @author: tjshan
 * @date: 2020-04-12 17:02
 * FileName: PipelineTest
 * Description:
 */
@Slf4j
public class PipelineTest {

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
    public void mdelTest(){
        List<String> strings;
        Pipeline pipeline = jedis.pipelined();
        String[] keys = {"a","b","c"};
        jedis.mset("a","123","b","456","c","789");
        strings = jedis.mget(keys);
        log.info(strings.toString());
        List<String> keyList = Arrays.asList(keys);
        this.mdel(keyList,pipeline);
        strings = jedis.mget(keys);
        log.info(strings.toString());
    }


    private void mdel(List<String> keys,Pipeline pipeline){
        for (String key : keys){
            pipeline.del(key);
        }
//        pipeline.sync();
        List<Object> list = pipeline.syncAndReturnAll();
        log.debug(list.toString());
    }

}
