package com.chengshi.shiro.controller;

import com.chengshi.shiro.vo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-03 23:23
 */
@Controller
public class IndexController {

    @ResponseBody
    @PostMapping(value = "/subLogin",produces = "application/json;charset=utf-8")
    public String subLogin(User user){
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(user.getUsername(),user.getPassword());
        try {
            token.setRememberMe(user.isRememberMe());
            if (!subject.isAuthenticated()){
                subject.login(token);
            }
        } catch (AuthenticationException e) {
            return e.getMessage();
        }

        if (subject.hasRole("admin")){
            return "有admin权限";
        }
        return "没有admin权限";
    }

    @ResponseBody
    @GetMapping("/ping")
    public String  ping(){
        return "ping success !";
    }

//    @RequiresRoles("admin")
//    @ResponseBody
//    @GetMapping("/testAdmin")
//    public String testRole1(){
//        return "admin role";
//    }
//
//    @RequiresRoles("user")
//    @ResponseBody
//    @GetMapping("/testUser")
//    public String testRole2(){
//        return "user role";
//    }

    @ResponseBody
    @GetMapping("/roleAdmin")
    public String testRole1(){
        return "admin role";
    }

    @ResponseBody
    @GetMapping("/roleUser")
    public String testRole2(){
        return "user role";
    }

    @ResponseBody
    @GetMapping("/roleTest")
    public String testRole3(){
        return "user role";
    }

    @ResponseBody
    @GetMapping("/perms1")
    public String testPerms1(){
        return "permission 1";
    }

    @ResponseBody
    @GetMapping("/perms2")
    public String testPerms2(){
        return "permission 2";
    }

    @ResponseBody
    @GetMapping("/perms3")
    public String testPerms3(){
        return "permission 3";
    }
}
