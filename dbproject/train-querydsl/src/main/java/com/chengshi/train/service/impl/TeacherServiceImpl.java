package com.chengshi.train.service.impl;

import com.chengshi.train.dao.TeacherRepository;
import com.chengshi.train.model.QTeacher;
import com.chengshi.train.model.Teacher;
import com.chengshi.train.service.ITeacherService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 使用spring data QueryDSL实现
 * @author: tian
 * @date: 2019-01-14 23:00
 */
@Service
public class TeacherServiceImpl implements ITeacherService {
    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public Teacher findTeacherById(Integer id) {
        QTeacher qTeacher=QTeacher.teacher;
        Predicate predicate=qTeacher.id.eq(id);
        return teacherRepository.findOne(predicate);
    }

    @Override
    public List<Teacher> findTeacherList() {
        QTeacher qTeacher=QTeacher.teacher;
        return teacherRepository.findAll();
    }

    @Override
    public List<Teacher> findTeacherByName(String value) {
        QTeacher qTeacher=QTeacher.teacher;
        Predicate predicate=qTeacher.teacherName.eq(value);
        return (List<Teacher>) teacherRepository.findAll();
    }
}
