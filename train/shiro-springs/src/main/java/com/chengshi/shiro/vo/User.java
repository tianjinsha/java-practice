package com.chengshi.shiro.vo;

import lombok.Data;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-06 19:16
 */
@Data
public class User {
    private String username;
    private String password;
    private boolean rememberMe;
}
