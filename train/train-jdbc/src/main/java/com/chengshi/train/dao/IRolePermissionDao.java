package com.chengshi.train.dao;

import com.chengshi.train.model.RolePermission;

import java.util.List;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-10 23:32
 */
public interface IRolePermissionDao {

    List<String> getPermissions(String roleName);
    int create (RolePermission rolePermission);
    int delete(int id);
    int delete(String roleName,String permission);
}
