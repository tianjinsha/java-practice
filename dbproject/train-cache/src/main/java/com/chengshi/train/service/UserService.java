package com.chengshi.train.service;

import com.chengshi.train.dao.UserRepository;
import com.chengshi.train.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-26 16:45
 */
@CacheConfig(cacheNames = "user1")
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    /**
     * 新增用户
     */
    @CachePut(key="#user.username")
    public User insertUser(User user){
        return userRepository.save(user);
    }

    /**
     * 通过id查找单个用户
     */
    @Cacheable(key="#username")
    public User getUserByUserName(String username){
        return userRepository.findUserByUsername(username);
    }

    /**
     * 通过id修改单个用户
     */
    @CachePut(key = "#user.username")
    public User updateUserByUserName(User user){
        return userRepository.saveAndFlush(user);
    }

    /**
     * 通过id删除单个用户
     */
    @CacheEvict(key = "#username")
    public void deleteUserByUserName(String username){
        userRepository.deleteUserByUsername(username);
    }
}
