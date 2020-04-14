package com.grg.redis.jedis;

import com.grg.redis.jedis.bean.Person;
import com.grg.redis.jedis.utils.ProtostuffUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ConnectTest {

    private  Jedis jedis = null;

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
    public void stringTest() throws InterruptedException {

        jedis.set("company","GrgBanking");
        String company = jedis.get("company");
        log.info(company);

        jedis.set("number","1");
        jedis.incr("number");
        jedis.expire("number",60);
        String number =  jedis.get("number");
        log.info(number);
        TimeUnit.SECONDS.sleep(2);
        Long ttl = jedis.ttl("number");
        log.info(ttl.toString());

    }

    @Test
    public void hashTest(){

        jedis.hset("person","age","24");
        jedis.hset("person","name","tjshan");
        jedis.hset("person","school","chengdushifan");
        jedis.hset("person","job","java develop");
        jedis.hset("person","company","grgBanking");

        Map<String, String> person = jedis.hgetAll("person");
        log.info(person.toString());

    }

    @Test
    public void setTest(){
        jedis.sadd("sets","1");
        jedis.sadd("sets","2");
        jedis.sadd("sets","3");

        Set<String> sets = jedis.smembers("sets");

        System.out.println(sets);
    }

    @Test
    public void zSetTest(){
        jedis.zadd("zsets",23.0,"toms");
        jedis.zadd("zsets",23.0,"peter");
        jedis.zadd("zsets",43.0,"jack");

        Set<String> zsets = jedis.zrange("zsets", 0, 100);
        System.out.println(zsets);
    }

    @Test
    public void serializeTest(){
        Person person = Person.builder().id(1).name("tjshan").job("IT Enginerr").sex("男").address("GuangZhou").build();
        String key = "person:1";
        jedis.set(key.getBytes(), ProtostuffUtils.serialize(person));

        byte[] data = jedis.get(key.getBytes());
        System.out.println(ProtostuffUtils.deserialize(data,Person.class));
    }

}
