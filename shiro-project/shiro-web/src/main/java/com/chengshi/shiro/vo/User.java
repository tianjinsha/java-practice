package com.chengshi.shiro.vo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    private String username;
    private String password;
    private String salt;
    private boolean rememberMe;
}
