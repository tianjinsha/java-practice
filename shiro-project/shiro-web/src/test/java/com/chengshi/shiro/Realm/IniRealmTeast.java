package com.chengshi.shiro.Realm;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

@Slf4j
public class IniRealmTeast {
    IniRealm iniRealm;
    @Before
    public void init(){
        iniRealm=new IniRealm("classpath:user.ini");
    }
    @Test
    public void testAuthentication(){
        DefaultSecurityManager securityManager=new DefaultSecurityManager();
        securityManager.setRealm(iniRealm);

        SecurityUtils.setSecurityManager(securityManager);
        Subject crrentUser=SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken("zhangsan","123456");

        try {
            crrentUser.login(token);
        } catch (AuthenticationException e) {
            log.info(e.getMessage());
        }

        log.info("isAuthenticated:"+crrentUser.isAuthenticated());
        log.info("role-admin:"+crrentUser.hasRole("admin"));
        log.info("role-test:"+crrentUser.hasRole("test"));
        log.info("permission-user:insert:"+crrentUser.isPermitted("user:insert"));
        log.info("permission-user:test:"+crrentUser.isPermitted("user:test"));
    }
}
