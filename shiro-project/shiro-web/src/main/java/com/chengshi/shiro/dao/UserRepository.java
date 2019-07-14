package com.chengshi.shiro.dao;

import com.chengshi.shiro.vo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("from users u where u.username=:username")
    User findUsersByUsername(@Param("username") String username);
}
