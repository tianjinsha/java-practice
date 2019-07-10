package com.chengshi.train.dao.impl;

import com.chengshi.train.dao.IRolePermissionDao;
import com.chengshi.train.model.RolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-10 23:59
 */
@Repository
public class RolePermissionImpl implements IRolePermissionDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<String> getPermissions(String roleName) {
        return jdbcTemplate.queryForList("select permission from  role_permissions where role_name=?", String.class, roleName);
    }

    @Override
    public int create(RolePermission rolePermission) {
        return jdbcTemplate.update("insert into role_permissions set permission =?,role_name=?",
                rolePermission.getPermission(), rolePermission.getRoleName());
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("delete from role_permissions where id=?",id);
    }

    @Override
    public int delete(String roleName, String permission) {
        return jdbcTemplate.update("delete from role_permissions where role_name=? and permission=?",roleName,permission);
    }
}
