package com.chengshi.train.dao.impl;

import com.chengshi.train.dao.IUserRoleDao;
import com.chengshi.train.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-10 23:51
 */
@Repository
public class UserRoleDaoImpl implements IUserRoleDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<String> findRoles(String username) {
        return jdbcTemplate.queryForList("select role_name from user_roles where username=?", String.class, username);
    }

    @Override
    public int add(UserRole userRole) {
        return jdbcTemplate.update("insert into user_roles set username=?,role_name=?", userRole.getUsername(), userRole.getRoleName());
    }

    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update("delete from user_roles where id=?", id);
    }

    @Override
    public int delete(String username, String roleName) {
        return jdbcTemplate.update("delete from user_roles where username=? and roleName=?", username, roleName);
    }
}
