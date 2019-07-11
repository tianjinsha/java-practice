package com.chengshi.shiro.controller;

import com.chengshi.shiro.dao.UserDao;
import com.chengshi.shiro.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class IndexController {

    @Autowired
    UserDao userDao;

    @ResponseBody
    @PostMapping(value = "/loginUp",produces = "application/json;charset=utf-8")
    public String loginUp(User user){
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(user.getUsername(),user.getPassword());
        try {
            token.setRememberMe(user.isRememberMe());
            if (!subject.isAuthenticated()){
                subject.login(token);
            }
        } catch (AuthenticationException e) {
            return e.getMessage();
        }
        return "登陆成功";
    }

    @ResponseBody
    @GetMapping("/ping")
    public String ping(){
        User user=userDao.findUserByName("zhangsan");
        log.info(user.toString());
        return "ping success";
    }

    @ResponseBody
    @GetMapping("/logout")
    public String logout(){
        Subject subject= SecurityUtils.getSubject();
        subject.logout();
        return "退出";
    }

    @ResponseBody
    @GetMapping("/roleAdmin")
    public String roleAdmin(){
        return "有admin权限";
    }

    @ResponseBody
    @GetMapping("/roleUser")
    public String roleUser(){
        return "有user权限";
    }
}
