package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.jdbc.dto.UserDto;
import org.example.jdbc.entity.Role;
import org.example.jdbc.service.RoleService;
import org.example.jdbc.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author tjstj
 * @description TODO
 * @date 2020/12/13 17:19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserTest {

    @Autowired
    RoleService roleService;
    @Autowired
    UserService userService;

    @Test
    public void findUserByName() {
        String username = "user1";
        log.info("UserTest.findUserByName:"+username);
        UserDto user = userService.findUserByName(username);
        log.info(user.toString());

        username = "user2";
        log.info("UserTest.findUserByName:"+username);
        user = userService.findUserByName(username);
        log.info(user.toString());
    }

    @Test
    public void findUserByPhone(){
        String phone = "18780183730";
        log.info("UserTest.findUserByPhone:"+phone);
        UserDto user = userService.findUserByPhone(phone);
        log.info(user.toString());
    }

    @Test
    public void findUserByEmail(){
        String email = "18780183730@163.com";
        log.info("UserTest.findUserByEmail:"+email);
        UserDto user = userService.findUserByEmail(email);
        log.info(user.toString());
    }

    @Test
    public void findAllRoles(){
        log.info("UserTest.findAllRoles");
        List<Role> roles = roleService.findAll();
        roles.stream().forEach(item->{
            log.info(item.toString());
        });
    }

    @Test
    public void findByCode(){
        String code = "ROLE_SYSTEM";
        log.info("UserTest.findAllRoles:");
        Role role = roleService.findRoleByCode(code);
        log.info(role.toString());
    }
}
