package com.chenghsi.train.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="roles")
public class Role {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "role_name")
    private String roleName;
}
