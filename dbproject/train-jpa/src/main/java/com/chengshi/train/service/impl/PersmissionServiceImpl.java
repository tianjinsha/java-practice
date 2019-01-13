package com.chengshi.train.service.impl;

import com.chengshi.train.dao.RolePermissionRepository;
import com.chengshi.train.dao.UserRoleRepository;
import com.chengshi.train.service.IPersmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-13 22:39
 */
@Service
public class PersmissionServiceImpl implements IPersmissionService {

    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    RolePermissionRepository rolePermissionRepository;

    @Override
    public List<String> findRoleByUserName(String username) {
        return userRoleRepository.findRlesByUsername(username);
    }

    @Override
    public List<String> findPermissionByRoleName(String roleName) {
        return rolePermissionRepository.findPermissionByRoleName(roleName);
    }
}
