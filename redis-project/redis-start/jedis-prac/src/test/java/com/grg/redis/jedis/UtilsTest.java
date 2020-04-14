package com.grg.redis.jedis;

import com.grg.redis.jedis.bean.Person;
import com.grg.redis.jedis.utils.ProtostuffUtils;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author: tjshan
 * @date: 2020-04-12 15:32
 * FileName: UtilsTest
 * Description:
 */
public class UtilsTest {

    @Test
    public void serializerTest(){
        Person person = Person.builder().id(1).name("tjshan").job("IT Enginerr").sex("男").address("GuangZhou").build();
        byte[] data = ProtostuffUtils.serialize(person);
        System.out.println("序列化后：");
        System.out.println(Arrays.toString(data));

        Person result = ProtostuffUtils.deserialize(data, Person.class);
        System.out.println("反序列化：");
        System.out.println(result.toString());
    }
}
