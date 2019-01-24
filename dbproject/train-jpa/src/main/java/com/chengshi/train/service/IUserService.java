package com.chengshi.train.service;

import com.chengshi.train.model.User;
import org.springframework.data.domain.Page;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-13 16:56
 */
public interface IUserService {

    Boolean addUser(User user);

    Page<User> findUsers(Integer page, Integer size);
    Page<User> findUsers(Integer page, Integer size,User user);
}
