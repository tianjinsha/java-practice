package com.chengshi.test;

import com.chengshi.shiro.CustomRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-01 22:25
 */
public class CustomRealmTest {
    @Test
    public void testAuthentication(){
        CustomRealm customRealm=new CustomRealm();

        //1、创建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();
        defaultSecurityManager.setRealm(customRealm);

        //加密
        HashedCredentialsMatcher matcher=new HashedCredentialsMatcher();
        //设置加密算法
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1);
        customRealm.setCredentialsMatcher(matcher);

        //2、主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject=SecurityUtils.getSubject();

        UsernamePasswordToken token=new UsernamePasswordToken("Mark","123456");

        subject.login(token);

//        System.out.println("isAuthenticated:"+Boolean.toString(subject.isAuthenticated()));
//        subject.logout();
//        System.out.println("isAuthenticated:"+Boolean.toString(subject.isAuthenticated()));

        subject.checkRole("admin");
        subject.checkRole("user");

    }
}
