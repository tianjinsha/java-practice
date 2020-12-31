package org.example.security;

import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;
import org.example.dto.UserDto;
import org.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author tjstj
 * @description TODO
 * @date 2020/11/21 21:57
 */
@Component
public class OwnUserDetailServices implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(OwnUserDetailServices.class);

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("登陆用户:" + username);
//        System.out.println(passwordEncoder.encode("123456"));
        UserDto user = userService.findUserByName(username);

        return new User(username,
                user.getPassword(),
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
