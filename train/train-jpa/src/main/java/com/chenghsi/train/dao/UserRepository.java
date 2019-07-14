package com.chenghsi.train.dao;

import com.chenghsi.train.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserById(Integer id);

    User findUserByUsername(String username);

    @Query("from User where id=:id")
    User findUserWithId(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query(value = "delete  from users where username=:username", nativeQuery = true)
    Integer deleteUserByUsername(@Param("username") String username);

    @Transactional
    @Modifying
    @Query(value = "update users set status=:status where username=:username", nativeQuery = true)
    Integer updateUserStatus(@Param("username") String username, @Param("status") Integer status);

    //    Page<User> queryFirst2(Pageable pageable);
    Slice<User> findTop3ByStatus(Integer status, Pageable pageable);

    List<User> findFirst2ByStatus(Integer status, Sort sort);

    List<User> findTopByStatus(Integer Status, Pageable pageable);

    List<User> findTop2ByStatus(Integer status, Pageable pageable);

//    @Query(value = "select * from users where status=:status",
//            countQuery = "select count(*) from users where status=:status",
//            nativeQuery = true)
//    Page<User> findPage(@Param("status") Integer status, Pageable pageable);

}
