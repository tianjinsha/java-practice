package com.chengshi.train.session;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds= 1800, redisFlushMode = RedisFlushMode.ON_SAVE, redisNamespace = "train-session-2")
public class RedisSessionConfig {

}
