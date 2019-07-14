package com.grg.train.shiro.dao;

import com.grg.train.shiro.model.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @description:
 * @author: tian
 * @date: 2018-12-19 9:44
 */
@Slf4j
@Repository
public class MemberDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Member findById(Integer id){
        return  jdbcTemplate.queryForObject("select * from member where id=?",new Object[]{id},Member.class);
    }

    public Member findByName(String username){
        log.info("username");
        return jdbcTemplate.queryForObject("select * from member where username=?",new Object[]{username},Member.class);
    }

    public List<Member> findAll(){
        return jdbcTemplate.query("select * from member",new MemberRowMapper());
    }


    class MemberRowMapper implements RowMapper<Member> {
        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            Member member = new Member();
            member.setId(rs.getInt("id"));
            member.setUsername(rs.getString("username"));
            member.setPassword(rs.getString("password"));
            return member;
        }
    }
}
