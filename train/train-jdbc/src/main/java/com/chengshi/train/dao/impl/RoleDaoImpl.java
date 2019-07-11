package com.chengshi.train.dao.impl;

import com.chengshi.train.dao.IRoleDao;
import com.chengshi.train.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        List<Role> roles=jdbcTemplate.query("select * from roles",new Object[]{},new BeanPropertyRowMapper<>(Role.class));
        return roles;
    }

    @Override
    public int create(Role role) {
        return jdbcTemplate.update("insert into roles set role_name=?",role.getRoleName());
    }

    @Override
    public int deleteById(Integer id) {
        return jdbcTemplate.update("delete  from roles where id=?",id);
    }

    @Override
    public int deleteByName(String roleName) {
        return jdbcTemplate.update("delete  from roles where role_name=?",roleName);
    }

    class RoleRowMapper implements RowMapper<Role>{

        @Override
        public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
            Role role=new Role();
            role.setId(rs.getInt(rs.getInt("id")));
            role.setRoleName(rs.getString("role_name"));
            return role;
        }
    }
}
