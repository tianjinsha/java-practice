package org.example.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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

}
