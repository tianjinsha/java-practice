package com.chengshi.shiro.Realm;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-01 21:50
 */
@Slf4j
public class JdbcRealmTest {
    DruidDataSource dataSource=new DruidDataSource();

//    {
//        dataSource.setUrl("jdbc:mysql://localhost:3306/springdb?useUnicode=true&characterEncoding=utf-8&useSSL=false");
//        dataSource.setUsername("root");
//        dataSource.setPassword("123456@grg");
//    }





    @Test
    public void testAuthentication(){

        dataSource.setUrl("jdbc:mysql://localhost:3306/springdb?useUnicode=true&characterEncoding=utf-8&useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("123456@grg");
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

        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            log.info(e.getMessage());
        }

        log.info("isAuthenticated:"+Boolean.toString(subject.isAuthenticated()));

//        subject.logout();

        subject.checkRole("admin");
        subject.checkRole("user");
        subject.checkPermission("user_delete");
    }

    @Test
    public void TestAuthentication2(){

        Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro-jdbc.ini");

        SecurityManager securityManager=factory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        Subject currentUser=SecurityUtils.getSubject();

        UsernamePasswordToken token=new UsernamePasswordToken("zhangsan","123456");

        try {
            currentUser.login(token);
        } catch (AuthenticationException e) {
           log.info(e.getMessage());
        }

        log.info("isAuthenticated:"+currentUser.isAuthenticated());
    }
}
