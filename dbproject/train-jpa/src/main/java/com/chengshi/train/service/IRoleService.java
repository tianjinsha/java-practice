package com.chengshi.train.service;

import com.chengshi.train.model.Role;
import org.springframework.data.domain.Page;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-13 16:58
 */
public interface IRoleService {

    boolean addRole(Role role);
    boolean deleteRole(Integer id);
    Boolean updateRole(Role role);
    Page<Role> findRoleCriteria(Integer page,Integer size);
    Page<Role> findRoleCriteria(Integer page,Integer size,Role role);
    Role findRoleById(Integer id);

}
