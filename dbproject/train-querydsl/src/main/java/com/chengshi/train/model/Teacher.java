package com.chengshi.train.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-14 22:14
 */
@Data
@Table(name = "teacher")
@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String teacherName;
    private Integer age;
    private Integer sex;

    @OneToMany(mappedBy = "teacher",cascade={CascadeType.PERSIST,CascadeType.REMOVE},fetch = FetchType.EAGER)
    private List<Course> courses;
}
