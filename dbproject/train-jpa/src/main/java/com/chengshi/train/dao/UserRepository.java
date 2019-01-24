package com.chengshi.train.dao;

import com.chengshi.train.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-13 16:29
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>,JpaSpecificationExecutor<User> {

    int deleteUserByUsername(String username);

    int deleteUserById(Integer id);

    User findUserById(Integer id);

    User findUserByUsername(String username);


    @Transactional
    @Modifying
    @Query(value = "update users set status=:status where username=:username", nativeQuery = true)
    Integer updateUserStatus(@Param("username") String username, @Param("status") Integer status);
}
