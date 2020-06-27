package com.train.prac;

import com.train.prac.vo.Apple;
import com.train.prac.vo.Fruit;
import com.train.prac.vo.Person;
import org.junit.Test;

/**
 * @author: tjshan
 * @date: 2020-06-27 17:55
 * FileName: ObjectClass
 * Description:
 */
public class ObjectClass {

    @Test
    public void changeTest(){
        Fruit fruit = new Apple();
    }


    @Test
    public void changeTest2(){
        Fruit fruit = new Apple();
        Apple apple = (Apple) fruit;
    }

    @Test
    public void changeTest3(){
        Fruit fruit = new Fruit();
        Apple apple = (Apple) fruit; //error
    }

    @Test
    public void hashTest1(){
        Person person =new Person();
        person.setSex("男");
        person.setName("tiny");
        System.out.println(person.hashCode());

        person.setName("ant");
        System.out.println(person.hashCode());

    }

}
