package com.chengshi.train.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-14 22:13
 */
@Data
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String studentName;
    private Integer age;
    private Integer sex;

//    private List<Grade> grades;
//
//    private List<Course> courses;
//
//    private List<Teacher> teachers;
}
