package com.chengshi.shiro.vo;

import lombok.Data;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-03 23:27
 */
@Data
public class User {

    String username;
    String password;
    boolean rememberMe;
}
