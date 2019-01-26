package com.chengshi.train;

import com.chengshi.train.service.UserService;
import com.chengshi.train.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TrainCacheApplicationTests {

    @Autowired
    UserService userService;

    @Test
    public void TestInsert() {
        User user=new User();
        user.setUsername("zhangsan");
        user.setPassword("123456");
        user.setSalt("salt");
        user.setStatus(1);

    }

    @Test
    public void TestFind(){
        String username="admin";
        User user = userService.getUserByUserName(username);
        log.info("user:"+user);
    }

    @Test
    public void testDelete(){
        String username="yaoxun";
        userService.deleteUserByUserName(username);
    }

}

