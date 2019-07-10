package com.chengshi.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

@Slf4j
public class InitRealmTest2 {

    @Test
    public void testAuthentication(){

        Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:user.ini");
        SecurityManager securityManager=factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject=SecurityUtils.getSubject();
        Session session=subject.getSession();
        session.setAttribute("key","value");
        String value = (String) session.getAttribute("key");
        if ("value".equals(value)){
            log.info("检索到正确的值[" + value + "]");
        }

        if(!subject.isAuthenticated()){
            UsernamePasswordToken token=new UsernamePasswordToken("Mark","123456");
            token.setRememberMe(true);
            try {
                subject.login(token);
            } catch (AuthenticationException e) {
                log.info(e.getMessage());
            }
        }


    }
}
