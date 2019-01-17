package com.chengshi.train.dao;

import com.chengshi.train.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-14 22:52
 */
@Repository
public interface TeacherRepository  extends JpaRepository<Teacher, Integer> , QueryDslPredicateExecutor<Teacher> {
}
