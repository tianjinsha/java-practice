package com.chenghsi.train;

import com.chenghsi.train.dao.UserRepository;
import com.chenghsi.train.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TrainJpaTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testUserSelect() {
        User user;
        user = userRepository.findUserById(1);
        log.info(user.toString());
        user = userRepository.findUserByUsername("test");
        log.info(user.toString());
        user=userRepository.findUserWithId(1);
        log.info(user.toString());
    }

    @Test
    public void testUserInsert() {
        User user = new User();
        user.setId(9);
        user.setUsername("xiaoqi");
        user.setPassword("123456");
        user.setSalt("123456");
        user.setStatus(1);
        userRepository.save(user);
    }

    @Test
    public void testUserUpdate() {
        User user = new User();
        user.setId(6);
        user.setPassword("123456");
        user.setSalt("123456");
        user.setStatus(1);
        user.setUsername("xiaoqi3");
        userRepository.saveAndFlush(user);
    }

    @Test
    public void testUserDelete() {
        //userRepository.delete(9);
        userRepository.deleteUserByUsername("xiaoqi1");
    }

    @Test
    public void testUserStatusChange() {
        userRepository.updateUserStatus("xiaoqi3", 0);
    }

    @Test
    public void testUserPage(){
        List<User> users;
        Sort sort=new Sort(Sort.Direction.ASC,"id");
        Pageable pageable=new PageRequest(0,2,sort);
//      users = userRepository.findFirst2ByStatus(1, sort);
//       users= userRepository.findTopByStatus(1,pageable);
//        users =userRepository.findTop2ByStatus(1,pageable);
//        for (User user:users){
//            log.info("user:"+user.toString());
//        }
//        Page<User> page=userRepository.findPage(1,pageable);
        userRepository.findAll(pageable);
        log.info(""+userRepository.findAll(pageable).getTotalElements());

    }

}

