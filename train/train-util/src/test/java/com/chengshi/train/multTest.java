package com.chengshi.train;

import com.chengshi.train.lambda.Mult;
import org.junit.Test;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-20 10:46
 */
public class multTest {

    @Test
    public void test1(){
        Mult mult=new Mult() {
            @Override
            public void add(int a, int b) {
                System.out.println("add:" +(a+b));
            }
        };
        Mult mult2= (int a, int b) -> System.out.println("add:" +(a+b));

        mult.add(2,4);
        mult2.add(4,3);
    }
}
