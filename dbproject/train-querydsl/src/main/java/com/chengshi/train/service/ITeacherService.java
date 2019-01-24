package com.chengshi.train.service;

import com.chengshi.train.model.Teacher;

import java.util.List;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-14 22:57
 */
public interface ITeacherService {

    Teacher findTeacherById(Integer id);
    List<Teacher> findTeacherList();
    List<Teacher> findTeacherByName(String value);
}
