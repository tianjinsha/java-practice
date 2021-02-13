package org.example.security;

import lombok.extern.slf4j.Slf4j;
import org.example.jdbc.dto.UserDto;
import org.example.jdbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * @author tjstj
 * @description TODO
 * @date 2020/11/21 21:57
 */

@Slf4j
@Component("mobileUserDetailsService")
public class MobileUserDetailService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("登陆电话号码:" + username);
        UserDto user = userService.findUserByPhone(username);
        List<String> roles = user.getRoles();
        List<GrantedAuthority> authorities = new ArrayList(roles.size());
        roles.forEach(item->{
            authorities.add(new SimpleGrantedAuthority(item));
        });
        return new User(username,user.getPassword(),authorities);
    }
}
