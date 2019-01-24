package com.chengshi.train.dao;

import com.chengshi.train.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-13 22:23
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Integer> {

    @Query(value = "select role_name from user_roles where username=:username",nativeQuery = true)
    List<String> findRlesByUsername(@Param("username") String username);

    int deleteUserRoleById(Integer id);

    int deleteUserRoleByUsernameAndRoleName(String username,String roleName);

}
