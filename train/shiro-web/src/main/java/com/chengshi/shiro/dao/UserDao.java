package com.chengshi.shiro.dao;

import com.chengshi.shiro.vo.User;

import java.util.List;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-05 18:05
 */
public interface UserDao {

    User findUserByName(String username);

    List<String> findRoleByName(String username);
}
