package org.example.jdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author tjstj
 * @description TODO
 * @date 2020/12/13 15:20
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role {
    @Id
    @Column(unique = true)
    private String code;
    @Column
    private String name;
    @Column
    private String desc;

    @Override
    public String toString() {
        return "Role{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
