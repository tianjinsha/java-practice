package com.chengshi.shiro.dao;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

public class codeTest {

    @Test
    public void test(){
        Md5Hash md5Hash=new Md5Hash("123456","1234567");
        System.out.println(md5Hash.toString());
    }
}
