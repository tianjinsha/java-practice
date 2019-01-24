package com.chengshi.train.service.impl;

import com.chengshi.train.dao.UserRepository;
import com.chengshi.train.model.User;
import com.chengshi.train.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
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
 * @date: 2019-01-13 16:57
 */
@Component
public class UserServiceImpl implements IUserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public Boolean addUser(User user) {
        User save = userRepository.save(user);
        if (!ObjectUtils.isEmpty(save)){
            return true;
        }
       return false;
    }

    @Override
    public Page<User> findUsers(Integer page, Integer size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> findUsers(Integer page, Integer size, User user) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
        Page<User> userPage = userRepository.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                if (!StringUtils.isEmpty(user.getUsername())){
                    Predicate p1=cb.equal(root.get("username").as(String.class),user.getUsername());
                    list.add(p1);
                }
                if (!ObjectUtils.isEmpty(user.getStatus())){
                    Predicate p2=cb.equal(root.get("status").as(Integer.class),user.getStatus());
                    list.add(p2);
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        },pageable);
        return userPage;
    }
}
