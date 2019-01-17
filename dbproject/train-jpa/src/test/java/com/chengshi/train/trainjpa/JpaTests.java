package com.chengshi.train.trainjpa;

import com.chengshi.train.dao.RoleRepository;
import com.chengshi.train.model.Role;
import com.chengshi.train.model.User;
import com.chengshi.train.service.IPersmissionService;
import com.chengshi.train.service.IRoleService;
import com.chengshi.train.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class JpaTests {
    @Autowired
    IRoleService roleService;

    @Autowired
    IUserService userService;

    @Autowired
    IPersmissionService persmissionService;

    @Test
    public void contextLoads() {

    }

    @Test
    public void testRole(){

        Role role=new Role();
        role.setRoleName("def");
        role.setDescription("thisi is def");
//        log.info(roleService.addRole(role)+":");
        log.info(roleService.findRoleById(1).toString());
        log.info("----------------------------------------");
//        Page<Role> roles=roleService.findRoleCriteria(1,2);
        Role findRole=new Role();
        role.setRoleName("abc");
//        findRole.setId(2);
        Page<Role> roles=roleService.findRoleCriteria(1,2,findRole);
        long totalElements = roles.getTotalElements();
        int totalPages = roles.getTotalPages();
        int rolesNumber = roles.getNumber();
        log.info(rolesNumber+":"+totalPages+":"+totalElements);
        List<Role> content = roles.getContent();
        log.info("content:"+content.toString());
        Iterator<Role> iterator = roles.iterator();
        while (iterator.hasNext()){
            Role role1 = iterator.next();
            log.info("roles:"+role1.toString());
        }

    }

    @Autowired
    RoleRepository roleRepository;
    @Test
    public void testRole2(){
        int count = roleRepository.updateRole("test modify", "b");
        log.info("count:"+count);
    }

    @Test
    public void testUser(){

        User user=new User();
        user.setUsername("zhangjiu");
//        user.setPassword("123456");
        user.setStatus(1);
//        user.setSalt("1234567");
//        userService.addUser(user);

//        Page<User> users = userService.findUsers(1, 2);
        Page<User> users = userService.findUsers(0, 2,user);
        log.info("users:"+users.getContent().toString());
    }

    @Test
    public void testPermission(){

        List<String> admin = persmissionService.findRoleByUserName("admin");
        log.info("admin:"+admin.toString());
        persmissionService.findPermissionByRoleName("admin");


    }
}

