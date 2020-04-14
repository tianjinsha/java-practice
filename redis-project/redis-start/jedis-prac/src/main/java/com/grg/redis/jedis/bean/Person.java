package com.grg.redis.jedis.bean;

import lombok.Builder;
import lombok.Data;

/**
 * @author: tjshan
 * @date: 2020-04-10 23:59
 * FileName: Person
 * Description: 用户类
 */
@Data
@Builder
public class Person {

    private Integer id;

    private String name;

    private String sex;

    private  String job;

    private String address;
}
