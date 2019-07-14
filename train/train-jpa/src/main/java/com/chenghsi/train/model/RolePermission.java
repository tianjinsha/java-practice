package com.chenghsi.train.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "role_permissions")
public class RolePermission {
    @Id
    @GeneratedValue
    private Integer id;
    private String permission;
    @Column(name="role_name")
    private String roleName;
}
