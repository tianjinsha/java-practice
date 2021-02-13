package org.example.jdbc.service;

import org.example.jdbc.entity.Role;
import org.example.jdbc.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: tjshan
 * @date: 2021/1/16 2:05 下午
 * FileName: RoleService
 * Description:
 */
@Service
public class RoleService {

    @Autowired
    RoleRepository repository;

    public Role findRoleByCode(String code) {
        Role role = repository.findByCode(code);
        return role;
    }

    public Role findRoleByName(String name) {
        Role role = repository.findByName(name);
        return role;
    }

    public List<Role> findAll() {
        List<Role> roles = repository.findAll();
        return roles;
    }
}
