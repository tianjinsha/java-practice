package com.chengshi.train.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Member implements Serializable {
    private String username;
    private Integer age;

    public Member() {
    }

    public Member(String username, Integer age) {
        this.username = username;
        this.age = age;
    }
}
