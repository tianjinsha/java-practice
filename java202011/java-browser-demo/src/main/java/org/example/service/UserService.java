package org.example.service;

import org.example.dto.UserDto;
import org.example.entity.User;
import org.example.mapper.UserMapper;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tjstj
 * @description TODO
 * @date 2020/12/13 23:01
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return UserDto
     */
    public UserDto findUserByName(String username){
        User user = userRepository.findByUsername(username);
        UserDto oneUser = UserMapper.INSTANCE.findOneUser(user);
        return oneUser;
    }

    /**
     * 根据电话号码查找用户
     * @param phone 电话号码
     * @return UserDto
     */
    public  UserDto findUserByPhone(String phone){
        User user = userRepository.findByPhone(phone);
        UserDto oneUser = UserMapper.INSTANCE.findOneUser(user);
        return oneUser;
    }

    /**
     * 根据电话号码查找用户
     * @param email 电子邮件
     * @return UserDto
     */
    public  UserDto findUserByEmail(String email){
        User user = userRepository.findByEmail(email);
        UserDto oneUser = UserMapper.INSTANCE.findOneUser(user);
        return oneUser;
    }


}
