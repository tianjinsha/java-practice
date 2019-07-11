package com.chengshi.shiro.realm;

import com.chengshi.shiro.dao.UserDao;
import com.chengshi.shiro.vo.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-06 19:30
 */
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    UserDao userDao;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username= (String) principalCollection.getPrimaryPrincipal();
        Set<String> roles=getRoleByUserName(username);
        Set<String> permissions=getPermissionsRoleName(username);

        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1、从主体传过来的认证信息中获取用户名
        String username = (String) authenticationToken.getPrincipal();
        //2、通过用户名到数据库中获取凭证
        String password = getPasswordByUserName(username);
        if (password==null){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(username,password,"customRealm");
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(username));
        return authenticationInfo;
    }

    /**
     * 模拟数据库查询
     * @param username
     * @return
     */
    private String getPasswordByUserName(String username) {
//        return userMap.get(username);
        User user=userDao.findUserByName(username);
        return user.getPassword();
    }

    /**
     * 模拟数据库查询
     * @param username
     * @return
     */
    private Set<String> getRoleByUserName(String username) {
        System.out.println("从数据库中获取授权信息");
        List<String> list=userDao.findRoleByName(username);
        Set<String> sets=new HashSet<>(list);
        return sets;


    }

    private Set<String> getPermissionsRoleName(String RoleName) {
        Set<String> sets=new HashSet<>();
        sets.add("user_delete");
        sets.add("user_update");
        return sets;
    }
}
