package com.chengshi.train.dao;

import com.chengshi.train.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-14 22:54
 */
@Repository
public interface CourseRepository extends JpaRepository<Course,Integer>, QueryDslPredicateExecutor<Course> {
}
