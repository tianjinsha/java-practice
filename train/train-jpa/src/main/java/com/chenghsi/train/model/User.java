package com.chenghsi.train.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String salt;
    private Integer status;
    @OneToMany(mappedBy = "",fetch = FetchType.LAZY)
    private Set<UserRole> roles;
}
