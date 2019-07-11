package com.cehngshi.train.model;

import lombok.Data;

@Data
public class Member {
    private Integer id;
    private String username;
    private String password;
    private String salt;
    private Integer status;
}
