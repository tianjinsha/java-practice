package com.chengshi.shiro.controller;

import com.chengshi.shiro.vo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-06 15:00
 */
@Controller
public class IndexController {

    @ResponseBody
    @RequestMapping(value = "/loginIn",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String login(User user){
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

        return "登录成功！";
    }

    @ResponseBody
    @GetMapping("/ping")
    public String ping(){
        return "ping success !";
    }
}
