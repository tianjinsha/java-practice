package com.chengshi.shiro.Realm;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

@Slf4j
public class AuthenticationTest {
    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void addUser() {
        simpleAccountRealm.addAccount("zhangsan", "123456","admin");
        simpleAccountRealm.addAccount("lisi", "123456","admin","user");
        simpleAccountRealm.addAccount("wangwu", "123456","user");
    }

    @Test
    public void testAuthentication() {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(simpleAccountRealm);

        SecurityUtils.setSecurityManager(securityManager);
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("lisi", "123456");
        token.setRememberMe(true);
        try {
            currentUser.login(token);
        } catch (AuthenticationException e) {
            log.info(e.getMessage());
        }
        log.info("isAuthenticated:" + currentUser.isAuthenticated());
        log.info("role-admin:"+Boolean.toString(currentUser.hasRole("admin")));

    }
}
