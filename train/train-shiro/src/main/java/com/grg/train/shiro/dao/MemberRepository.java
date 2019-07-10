package com.grg.train.shiro.dao;

import com.grg.train.shiro.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @author: tian
 * @date: 2018-12-18 19:45
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

    @Query("from member m where m.username=:username")
    Member findMember(@Param("username") String username);

    Member findByUsername(String username);

    @Override
    List<Member> findAll();
}
