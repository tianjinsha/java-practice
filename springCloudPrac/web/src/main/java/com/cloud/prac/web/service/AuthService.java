package com.cloud.prac.web.service;

import com.cloud.prac.web.sercurity.CurrentUserHolder;
import org.springframework.stereotype.Component;

/**
 * @author tjshan
 * @date 2019/10/4 12:26
 */
@Component
public class AuthService {
    private static  final String SUPER_USER="admin";

    public void checkAccess(){
        String user= CurrentUserHolder.get();
        if(!SUPER_USER.equals(user)){
            throw new RuntimeException("operation is not allow");
        }
    }
}
