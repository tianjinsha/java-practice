package com.chengshi.train.dao;

import com.chengshi.train.model.User;
import com.chengshi.train.util.Pagination;
import com.chengshi.train.util.page.Page;

import java.util.List;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-10 22:22
 */
public interface IUserDao {
    /**
     * 新增一个用户
     * @param user
     */
    int create(User user);

    /**
     *
     * 根据用户名查找用户
     * @param username
     * @return
     */
    User findUserByName(String username);

    /**
     * 查找所有用户
     * @return
     */
    List<User> findUserList();

    /**
     * 根据name删除一个用户
     * @param username
     */
    int deleteByName(String username);

    /**
     * 更新一个用户
     * @param user
     */
    int update(User user);

    /**
     * 获取用户总量
     */
    int getUserCount();


    Page page(Integer currentPage, Integer numPerPage);
}
