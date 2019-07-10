package com.grg.train.shiro.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @description:
 * @author: tian
 * @date: 2018-12-18 11:37
 */
@Data
@Entity(name = "member")
public class Member {

    public Member() {
    }

    public Member(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String username;

    @Column(nullable=false)
    private String password;

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
