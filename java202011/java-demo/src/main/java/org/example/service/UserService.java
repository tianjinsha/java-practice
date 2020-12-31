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
        User repository = userRepository.findByUsername(username);
        UserDto oneUser = UserMapper.INSTANCE.findOneUser(repository);
        return oneUser;
    }

}
