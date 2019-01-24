package com.chengshi.train.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Member {

    private String username;
    @JsonIgnore
    private String password;
    private int age;

    public Member() {
    }

    public Member(String username, String password, int age) {
        this.username = username;
        this.password = password;
        this.age = age;
    }
}
