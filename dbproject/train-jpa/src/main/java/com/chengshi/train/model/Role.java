package com.chengshi.train.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role_name")
    private String roleName;

    private String description;
}
