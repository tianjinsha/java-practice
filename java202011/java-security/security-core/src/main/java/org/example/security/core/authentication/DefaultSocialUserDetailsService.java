package org.example.security.core.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

/**
 * @author tjstj
 * @description 默认的SocialUserDetailsService实现
 * @date 2021/2/15 19:42
 */
@Slf4j
public class DefaultSocialUserDetailsService implements SocialUserDetailsService {

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        log.warn("请配置 SocialUserDetailsService 接口的实现.");
        throw new UsernameNotFoundException(userId);
    }
}
