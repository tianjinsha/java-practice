package com.chengshi.train;

import com.alibaba.fastjson.JSON;
import com.chengshi.train.Method_2.RedisUtil_2;
import com.chengshi.train.method_1.RedisUtil_1;
import com.chengshi.train.model.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TrainRedisApplicationTests {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisUtil_1 redisUtil_1;

    @Autowired
    RedisUtil_2 redisUtil_2;

    @Test
    public void redisTest_1() {

//        stringRedisTemplate.opsForValue().append("train:key4","value255");
//        stringRedisTemplate.opsForValue().append("train:key5","value5t3");
//        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
//        String result=operations.get("key4");
//        log.info("result:"+result);



    }

    @Test
    public void redisTest_2() {
        redisUtil_1.set("test1","test2");
        log.info("count:"+redisUtil_1.get("test"));
        List<String> list_1=new ArrayList<>();
        list_1.add("test1");
        list_1.add("test2");
        redisUtil_1.lRightPushAll("list_1",list_1);

    }


    @Test
    public void redisTest_3(){
        redisUtil_2.set("test1","new Value");
       log.info("redis::"+redisUtil_2.get("test1"));

        Member member1=new Member("zhangsan",24);
        redisUtil_2.set(member1.getUsername(),member1);
        Member zhangsan = (Member) redisUtil_2.get("zhangsan");
        log.info(zhangsan.toString());

        Member member2=new Member("lisi",45);
        Map map=new HashMap();
        map.put(member1.getUsername(),member1);
        map.put(member2.getUsername(),member2);
        redisUtil_2.hmset("map",map);

        List<Member> list=new ArrayList<>();
        list.add(member1);
        list.add(member2);
        redisUtil_2.lSet("list",list);

//        redisUtil_2.set("count",1);
        redisUtil_2.incr("count",2);

//        Member zhangshan = (Member) redisUtil_2.get("zhangshan");
//        log.info(zhangshan.toString());
    }

    @Test
    public void test4(){
        Member member1=new Member("zhangsan",24);
        Member member2=new Member("lisi",45);
        Map map=new HashMap();
        map.put(member1.getUsername(),member1);
        map.put(member2.getUsername(),member2);
        String s1 = JSON.toJSONString(map);
        redisUtil_1.set("json_map",s1);

        List<Member> list=new ArrayList<>();
        list.add(member1);
        list.add(member2);

        String s2 = JSON.toJSONString(list);
        redisUtil_1.set("json_list",s2);

        redisUtil_1.set("json_object",JSON.toJSONString(member1));

    }


    @Test
    public void test5(){
        Member member1=new Member("zhangsan",24);
        Member member2=new Member("lisi",45);

        List<Member> list=new ArrayList<>();
        list.add(member1);
        list.add(member2);
        redisUtil_2.set("list",list);

        List<Member> list2 = (List<Member>) redisUtil_2.get("list");

        log.info(list2.toString());
    }
}

