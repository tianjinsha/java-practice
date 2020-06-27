package com.train.prac.vo;

/**
 * @author: tjshan
 * @date: 2020-06-25 16:18
 * FileName: Person
 * Description:
 */
public class Person {
    private Integer age;
    private String name;
    private String sex;
    private String address;


    public Person() {
    }

    public Person(Integer age, String name, String sex, String address) {
        this.age = age;
        this.name = name;
        this.sex = sex;
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return name+":"+sex+":"+age+":"+address;
    }
}
