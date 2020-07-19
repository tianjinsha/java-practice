package com.train.prac;

import com.train.prac.comparatorUtil.PersonComparator;
import com.train.prac.vo.Person;
import com.train.prac.vo.Student;
import org.junit.Test;

import java.util.Arrays;

public class SortObjectTest {

    @Test
    public void comparableTest(){
        Student one = new Student("one", 34);
        Student two = new Student("two", 12);
        Student three = new Student("three", 25);

        Student[] student = {one,two,three};
        Arrays.sort(student);
        Arrays.stream(student).forEach(student1 -> {
            System.out.println(student1);
        });
    }

    @Test
    public void comparatorTest(){
        Person one = new Person(34,"one","男","");
        Person two = new Person(12,"two","女","");
        Person three = new Person(25,"three","男","");
        Person[] person = {one,two,three};
        Arrays.sort(person,new PersonComparator());

        Arrays.stream(person).forEach(item -> {
            System.out.println(item);
        });
    }
}
