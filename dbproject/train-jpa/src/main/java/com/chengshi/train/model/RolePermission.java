package com.chengshi.train.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "role_permissions")
public class RolePermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String permission;

    @Column(name="role_name")
    private String roleName;
}
