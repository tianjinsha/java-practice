package com.chengshi.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @description: IniRealm
 * @author: tian
 * @date: 2019-01-01 21:32
 */
public class IniRealmTest {
    IniRealm iniRealm=new IniRealm("classpath:user.ini");

//    @Before
//    public void addUser() {
//
//    }

    @Test
    public void testAuthentication(){
        //1、创建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(iniRealm);
        //2、主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("Mark", "123456");

        subject.login(token);
//
//        System.out.println("isAuthenticated:"+Boolean.toString(subject.isAuthenticated()));
//        subject.logout();
//        System.out.println("isAuthenticated:"+Boolean.toString(subject.isAuthenticated()));

//        subject.checkRole("user");
//        Set<String> roles=new HashSet<>();
//        roles.add("user");
//        roles.add("admin");
//        subject.checkRoles(roles);
//        subject.checkRoles("user","admin");

        subject.checkPermission("user_delete");
        subject.checkPermissions("user_delete","user_update");
    }
}
