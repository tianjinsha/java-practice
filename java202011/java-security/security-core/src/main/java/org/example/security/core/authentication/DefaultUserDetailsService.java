package org.example.security.core.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author tjstj
 * @description TODO
 * @date 2021/2/15 19:41
 */
@Slf4j
public class DefaultUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.warn("请配置 UserDetailsService 接口的实现.");
        throw new UsernameNotFoundException(username);
    }
}
