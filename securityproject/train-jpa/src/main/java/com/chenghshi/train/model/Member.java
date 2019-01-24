package com.chenghshi.train.model;

import lombok.Data;

import java.util.List;

@Data
public class Member {
    private Integer userId;
    private String username;
    private String password;
   private List<String> roles;

    public Member(String username, String password, List<String> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
