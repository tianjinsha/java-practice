package com.chengshi.train.config;

import lombok.Data;

@Data
public class RedisPoolProperties {

    private int maxIdle  = 8;

    private int minIdle  = 8;

    private int maxActive = 8;

    private int maxWait = -1;

    private int maxTotal=8;
}
