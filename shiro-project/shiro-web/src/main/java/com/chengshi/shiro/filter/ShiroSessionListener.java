package com.chengshi.shiro.filter;

import com.chengshi.shiro.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ShiroSessionListener implements SessionListener {
    @Autowired
    RedisUtil redisUtil;

    @Override
    public void onStart(Session session) {

    }

    @Override
    public void onStop(Session session) {
        log.info("onStop===" + session.getId());
        redisUtil.remove(session.getId().toString());
    }

    @Override
    public void onExpiration(Session session) {
        log.info("onExpiration===" + session.getId());
        redisUtil.remove(session.getId().toString());
    }
}
