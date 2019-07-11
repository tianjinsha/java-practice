package com.chengshi.train.dao;

import com.chengshi.train.model.UserRole;

import java.util.List;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-10 23:25
 */
public interface IUserRoleDao {
    List<String> findRoles(String username);
    int add(UserRole userRole);
    int deleteById(int id);
    int delete(String username,String roleName);
}
