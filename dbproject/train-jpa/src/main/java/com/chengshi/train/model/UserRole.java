package com.chengshi.train.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="user_roles")
public class UserRole {
    @Id
    @GeneratedValue
    private Integer id;

    private String username;

    @Column(name = "role_name")
    private String roleName;
}
