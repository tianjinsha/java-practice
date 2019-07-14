package com.chengshi.shiro.fileter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-05 21:32
 */
public class RoleOrFilter extends AuthorizationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Subject subject=getSubject(request,response);
        String[] roles= (String[]) mappedValue;
        if (roles==null || roles.length==0){
            return true;
        }
        for (String role : roles){
            if (subject.hasRole(role))
                return true;
        }
        return false;
    }
}
