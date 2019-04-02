package com.chengshi.train;

import com.alibaba.fastjson.JSON;
import com.chengshi.trai.JsonUtil;
import com.chengshi.trai.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FastJsonTest {

    @Test
    public void test(){
        User user=new User(1,"zhangsan");
        User user1=new User(2,"lisi");
        List<User> list=new ArrayList<>();
        list.add(user);
        list.add(user1);
        String s = JSON.toJSONString(list);

        System.out.println(s);
    }

    @Test
    public void test2() throws FileNotFoundException {
        File file=new File("track1.json");
        InputStream in=new FileInputStream(file);
    }
}
