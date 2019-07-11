package com.chengshi.shiro.dao;

import com.chengshi.shiro.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class JpaTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserDao userDao;

    @Test
    public void testJpa(){
        User user=new User();
        user=userRepository.findUsersByUsername("zhangsan");

        log.info(user.toString());
    }

    @Test
    public void testJdbc(){
        User user=userDao.findUserByName("zhangsan");
        log.info(user.toString());
    }
}
