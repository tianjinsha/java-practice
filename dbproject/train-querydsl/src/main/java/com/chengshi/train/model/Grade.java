package com.chengshi.train.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-14 22:14
 */
@Data
@Entity
@Table(name = "grade")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    @Column(name = "student_id")
//    private Integer studentId;
//    @Column(name="course_id")
//    private Integer courseId;
    private  Integer score;

    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.REMOVE,CascadeType.PERSIST},fetch = FetchType.EAGER)
    private Student student;
    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.REMOVE,CascadeType.PERSIST},fetch = FetchType.EAGER)
    private Course course;
}
