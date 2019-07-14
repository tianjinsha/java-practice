package com.chengshi.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-01 20:28
 */
public class AuthenticationTest {

    SimpleAccountRealm simpleAccountRealm=new SimpleAccountRealm();

    @Before
    public void addUser(){
        simpleAccountRealm.addAccount("Mark","123456","admin","user");
    }

    @Test
    public void testAuthentication(){
        //1、创建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);
        //2、主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject=SecurityUtils.getSubject();

        UsernamePasswordToken token=new UsernamePasswordToken("Mark","123456");

        subject.login(token);

//        System.out.println("isAuthenticated:"+Boolean.toString(subject.isAuthenticated()));
//        subject.logout();
//        System.out.println("isAuthenticated:"+Boolean.toString(subject.isAuthenticated()));

        try {
            subject.checkRole("admin");
            subject.checkRoles("admin","user");
        } catch (UnauthorizedException e) {
            System.out.println(e.getMessage());
        }
    }
}
