package org.example.security.app.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author tjstj
 * @description TODO
 * @date 2021/1/31 22:30
 */
@Slf4j
@Component
public class JwtTokenUtil implements Serializable {

    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
}
