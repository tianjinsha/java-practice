package org.example.repository;

import org.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * @author tjstj
 * @description TODO
 * @date 2020/12/13 14:41
 */
@Repository
public interface  UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 根据电话号码查找用户
     * @param phone
     * @return
     */
    User findByPhone(String phone);

    /**
     * 根据邮箱查找用户
     * @param email
     * @return
     */
    User findByEmail(String email);
}
