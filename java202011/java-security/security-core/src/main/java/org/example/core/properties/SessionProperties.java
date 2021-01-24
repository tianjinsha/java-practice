package org.example.core.properties;

import lombok.Data;

/**
 * @author: tjshan
 * @date: 2021/1/17 1:41 下午
 * FileName: SessionProperties
 * Description:
 */
@Data
public class SessionProperties {
    /**
     * 同一个用户在系统中的最大session数，默认1
     */
    private int maximumSessions = 1;
    /**
     * 达到最大session时是否阻止新的登录请求，默认为false，不阻止，新的登录会将老的登录失效掉
     */
    private boolean maxSessionsPreventsLogin;

    /**
     * session失效时跳转的页面
     */
    private String sessionInvalidPage  = SecurityConstants.DEFAULT_SESSION_INVALID_PAGE;
}
