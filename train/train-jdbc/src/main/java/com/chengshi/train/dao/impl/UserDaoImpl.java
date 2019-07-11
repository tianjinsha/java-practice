package com.chengshi.train.dao.impl;

import com.chengshi.train.dao.IUserDao;
import com.chengshi.train.model.User;
import com.chengshi.train.util.Pagination;
import com.chengshi.train.util.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.List;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-10 22:36
 */
@Repository
public class UserDaoImpl implements IUserDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    @Override
    public int create(User user) {
        final String sql = "insert into users(username,password,salt,status) values(?,?,?,?)";
        KeyHolder holder = new GeneratedKeyHolder();
        int count=jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,user.getUsername());
                ps.setString(2,user.getPassword());
                ps.setString(3,user.getSalt());
                ps.setInt(4,user.getStatus());
                return ps;
            }
        },holder);
        int userId=holder.getKey().intValue();
        return count;
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByName(String username) {
//        return jdbcTemplate.queryForObject("select * from users where username=?",new Object[]{username},new UserRowMapper());

        User user=new User();
        jdbcTemplate.query("select * from users where username=?",new Object[]{username}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setSalt(rs.getString("salt"));
                user.setStatus(rs.getInt("status"));
            }
        });
        return jdbcTemplate.queryForObject("select * from users where username=?",new Object[]{username},new UserRowMapper());
    }

    @Override
    public List<User> findUserList() {
        List<User> users=jdbcTemplate.query("select * from users",new Object[]{},new BeanPropertyRowMapper<>(User.class));
        return  users;
//        return jdbcTemplate.query("select * from users",new UserRowMapper());
    }

    @Override
    public int deleteByName(String username) {
        return jdbcTemplate.update("delete from USER where NAME = ?", username);
    }

    @Override
    public int update(User user) {
        final String sql = "update users set password=?,salt=?,status=? where username=?";
        int count=jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,user.getPassword());
                ps.setString(2,user.getSalt());
                ps.setInt(3,user.getStatus());
                ps.setString(4,user.getUsername());
                return ps;
            }
        });
        return count;
    }

    @Override
    public int getUserCount() {
        return jdbcTemplate.queryForObject("select count(1) from users", Integer.class);
    }

    class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user=new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setSalt(rs.getString("salt"));
            user.setStatus(rs.getInt("status"));
            return user;
        }
    }

    @Override
    public Page page(Integer currentPage,Integer numPerPage) {
        String sql="select * from users";
        Page page=new Page(sql, currentPage, numPerPage,jdbcTemplate);
        return page;
    }

    class UserRowCallbackHandler implements RowCallbackHandler{
        UserRowCallbackHandler(User user){

        }

        @Override
        public void processRow(ResultSet rs) throws SQLException {
            User user=new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setSalt(rs.getString("salt"));
            user.setStatus(rs.getInt("status"));
        }
    }
}
