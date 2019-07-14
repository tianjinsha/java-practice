package com.chenghsi.train.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="user_roles")
public class UserRole {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name="user_id")
    private Integer userId;
    @Column(name = "role_name")
    private String roleName;
}
