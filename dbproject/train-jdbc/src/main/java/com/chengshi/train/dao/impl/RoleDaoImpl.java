package com.chengshi.train.dao.impl;

import com.chengshi.train.dao.IRoleDao;
import com.chengshi.train.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-10 23:39
 */
@Repository
public class RoleDaoImpl implements IRoleDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Role> findRoleList() {
        List<Role> roles = jdbcTemplate.query("select * from roles", new Object[]{}, new BeanPropertyRowMapper<>(Role.class));
        return roles;
    }

    @Override
    public Role findRoleById(Integer id) {
        Role role = new Role();
        jdbcTemplate.query("select * from roles where id=?", new Object[]{id}, rs -> {
            role.setId(rs.getInt("id"));
            role.setRoleName(rs.getString("role_name"));
            role.setDescription(rs.getString("description"));
        });

//        jdbcTemplate.query("select * from users where username=?",new Object[]{id}, new RowCallbackHandler() {
//            @Override
//            public void processRow(ResultSet rs) throws SQLException {
//                role.setId(rs.getInt("id"));
//                role.setRoleName(rs.getString("role_name"));
//                role.setDescription(rs.getString("description"));
//            }
//        });

        return role;
    }

    @Override
    public int create(Role role) {
        return jdbcTemplate.update("insert into roles set role_name=?,description=?", role.getRoleName(), role.getDescription());
    }

    @Override
    public int deleteById(Integer id) {
        return jdbcTemplate.update("delete  from roles where id=?", id);
    }

    @Override
    public int deleteByName(String roleName) {
        return jdbcTemplate.update("delete  from roles where role_name=?", roleName);
    }
}
