package com.chenshi.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.StringUtils;
import org.junit.Test;
import org.springframework.data.redis.util.ByteUtils;
import org.springframework.util.SerializationUtils;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-06 21:52
 */
@Slf4j
public class CodeTest {

    @Test
    public void decode(){
        Md5Hash md5Hash=new Md5Hash("remember");
        byte[] bytes="remember".getBytes();
        log.info("md5:"+md5Hash.toString());
        log.info("md5:"+md5Hash.toBase64());
        log.info("md5:"+md5Hash.toHex());

        log.info("encode1:"+new String(Base64.encode(bytes)));
        log.info("encode2:"+Base64.encodeToString(bytes));
        log.info("encode3:"+Base64.encodeToString("cmVtZW1iZXI=".getBytes()));
        log.info("decode1:"+new String(Base64.decode("cmVtZW1iZXI=")));
        log.info("decode2:"+new String(Base64.decode("4AvVhmFLUs0KTA3Kprsdag==")));


        byte[] decode = Base64.decode("4AvVhmFLUs0KTA3Kprsdag==");
        Base64.encode(bytes);
        String code=new String(decode);
        System.out.println(code);
        log.info(decode.toString());
    }
}
