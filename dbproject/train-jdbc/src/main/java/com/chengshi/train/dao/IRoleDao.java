package com.chengshi.train.dao;

import com.chengshi.train.model.Role;

import java.util.List;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-10 23:33
 */
public interface IRoleDao {

    List<Role> findRoleList();
    Role findRoleById(Integer id);
    int create(Role role);
    int deleteById(Integer id);
    int deleteByName(String roleName);
}
