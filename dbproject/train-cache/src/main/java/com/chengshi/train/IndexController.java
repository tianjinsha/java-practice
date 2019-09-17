package com.chengshi.train;

import com.chengshi.train.config.RedisUtil;
import com.chengshi.train.service.UserService;
import com.chengshi.train.service.UserService_2;
import com.chengshi.train.model.User;
import com.chengshi.train.service.UserService_3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-26 17:08
 */
@RestController
public class IndexController {
    @Autowired
    UserService_2 userService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/ping")
    public String ping(){
        return "ping success !";
    }

    @GetMapping("/get/{username}")
    public User get(@PathVariable  String username){
        return userService.getUserByUserName(username);
    }

    @PostMapping("/insert/{username}")
    public User insert(@PathVariable String username){
        User user=new User();
        user.setUsername(username);
        user.setPassword("123456");
        user.setSalt("salt");
        user.setStatus(1);
       return userService.insertUser(user);
    }

    @PutMapping("/update/{id}")
    public  User update(@PathVariable  Integer id){
        User user=new User();
        user.setId(id);
        user.setUsername("yi");
        user.setPassword("123456");
        user.setSalt("salt-update");
        user.setStatus(0);
        return  userService.updateUserByUserName(user);
    }

    @DeleteMapping("/delete/{username}")
    public void delete(@PathVariable  String username){
        userService.deleteUserByUserName(username);
    }

    @GetMapping("/length")
    public Object getLength(@RequestParam  String prefix){
        String key=prefix+"*";

        Object o = redisUtil.keys(key);
        int i = redisUtil.keysLength(key);

        return o;
    }
}
