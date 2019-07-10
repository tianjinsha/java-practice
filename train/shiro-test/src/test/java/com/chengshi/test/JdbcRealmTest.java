package com.chengshi.test;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-01 21:50
 */
public class JdbcRealmTest {
    DruidDataSource dataSource=new DruidDataSource();

    {
        dataSource.setUrl("jdbc:mysql://localhost:3306/springdb?useUnicode=true&characterEncoding=utf-8&useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");
    }



    @Test
    public void testAuthentication(){
        JdbcRealm jdbcRealm=new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
        jdbcRealm.setPermissionsLookupEnabled(true);

        String sql="select password from member where username=?";
        String sqlRole="select role_name from user_roles where username = ?";
        String sqlPermission="select permission from roles_permissions where role_name = ?";
        jdbcRealm.setAuthenticationQuery(sql);
        jdbcRealm.setUserRolesQuery(sqlRole);
        jdbcRealm.setPermissionsQuery(sqlPermission);

        //1、创建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();
        defaultSecurityManager.setRealm(jdbcRealm);
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
        subject.checkPermission("user_delete");
    }
}
