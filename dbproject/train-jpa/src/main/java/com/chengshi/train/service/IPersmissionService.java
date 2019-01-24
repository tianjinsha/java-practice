package com.chengshi.train.service;

import java.util.List;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-13 22:36
 */
public interface IPersmissionService {

    List<String> findRoleByUserName(String username);

    List<String> findPermissionByRoleName(String roleName);

}
