package org.example.jdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author tjstj
 * @description TODO
 * @date 2020/12/13 14:26
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phone;
    @Column
    private String password;
    @Column
    private boolean isExpired;
    @Column
    private boolean isLocked;
    @Column
    private boolean isEnabled;
    @Column
    private Date createTime;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_code")
    )
    private List<Role> roles;
}
