package com.chengshi.train.service.impl;

import com.chengshi.train.dao.RoleRepository;
import com.chengshi.train.model.Role;
import com.chengshi.train.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-13 17:01
 */
@Service
@Slf4j
public class RoleServiceImpl implements IRoleService {

    public static final List<Predicate> LIST = new ArrayList<Predicate>();
    @Autowired
    RoleRepository roleReposity;

    @Override
    public boolean addRole(Role role) {
        Role value = roleReposity.save(role);
        if (ObjectUtils.isEmpty(value)){
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteRole(Integer id) {
        int cout= roleReposity.deleteRoleById(id);
       if (cout==1){
           return true;
       }
       return false;
    }

    @Override
    public Boolean updateRole(Role role) {
        Role value = roleReposity.saveAndFlush(role);
        if (ObjectUtils.isEmpty(value)){
            return false;
        }
        return true;
    }

    @Override
    public Page<Role> findRoleCriteria(Integer page, Integer size) {
        Pageable pageable=new PageRequest(page,size,Sort.Direction.ASC,"id");
        return roleReposity.findAll(pageable);
    }

    @Override
    public Page<Role> findRoleCriteria(Integer page, Integer size, Role role) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");

        Page<Role> rolePage=roleReposity.findAll(new Specification<Role>() {
            @Override
            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                if (!StringUtils.isEmpty(role.getRoleName())){
                    Predicate p1 = cb.equal(root.get("roleName").as(String.class), role.getRoleName());
                    query.where(cb.and(p1));
                }
                return query.getRestriction();

            }
        },pageable);
        return rolePage;
    }

    @Override
    public Role findRoleById(Integer id) {
        return roleReposity.findOne(id);
    }
}
