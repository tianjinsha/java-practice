package org.example.jdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author tjstj
 * @description TODO
 * @date 2020/12/13 15:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authority")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String pattern;
    @Column
    private String desc;
}
