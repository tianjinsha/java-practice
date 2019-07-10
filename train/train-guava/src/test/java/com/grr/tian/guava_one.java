package com.grr.tian;

import com.google.common.base.Optional;
import com.grg.train.util.Sum;
import org.junit.Test;


public class guava_one {

    @Test
    public void  testSum(){
        //java.lang.NullPointerException
//        System.out.println(Sum.sum_1(null,2));
        Integer invalidInput = null;
        Optional<Integer> a =  Optional.of(invalidInput);
        Optional<Integer> b =  Optional.of(new Integer(10));
        System.out.println(Sum.sum_2(a,b));
    }

    @Test
    public void testOptional(){
        Integer value1 = null;
        Integer value2 = new Integer(10);
        java.util.Optional<Integer> a = java.util.Optional.ofNullable(value1);
        java.util.Optional<Integer> b=java.util.Optional.of(value2);

        System.out.println("a::"+a);
        System.out.println("b::"+b);
    }

}
