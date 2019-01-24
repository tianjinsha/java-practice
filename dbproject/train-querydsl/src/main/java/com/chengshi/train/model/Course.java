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
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "course_name")
    private String courseName;


//    @Column(name = "teacher_id",insertable = false,updatable = false)
//    private Integer teacherId;

    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE, CascadeType.MERGE}, fetch=FetchType.EAGER)
    @JoinColumn(name="teacher_id")
    private Teacher teacher;

//    private List<Student> students;
}
