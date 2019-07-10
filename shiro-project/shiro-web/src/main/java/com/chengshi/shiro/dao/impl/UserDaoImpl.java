package com.chengshi.shiro.dao.impl;

import com.chengshi.shiro.dao.UserDao;
import com.chengshi.shiro.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-05 18:05
 */
@Component
public class UserDaoImpl  implements UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public User findUserByName(String username) {
        String sql="select username,password,password_salt from users where username=?";
        List<User> list=jdbcTemplate.query(sql, new String[]{username}, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user=new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setSalt(rs.getString("password_salt"));
                return user;
            }
        });
        if (CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<String> findRoleByName(String username) {
        String sql="select role_name from user_roles where username=?";

        return jdbcTemplate.query(sql,new String[]{username},new RowMapper<String>(){
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {

                return rs.getString("role_name");
            }
        });
    }
}
