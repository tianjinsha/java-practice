package com.train.prac;

import org.junit.Test;

/**
 * @author: tjshan
 * @date: 2020-06-27 22:13
 * FileName: AssertTest
 * Description:
 */
public class AssertTest {

    @Test
    public void assertTest(){
        int a = 1;
        int b = 1;
        int c = 2;
        assert a==c : "not equal";
    }
}
