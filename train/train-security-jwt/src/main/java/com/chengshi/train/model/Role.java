package com.chengshi.train.model;

import lombok.Data;

@Data
public class Role {
    private Integer id;
    private String roleName;

    public Role(String roleName) {
        this.roleName = roleName;
    }
}
