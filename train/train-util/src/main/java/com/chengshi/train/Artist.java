package com.chengshi.train;

import lombok.Data;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-20 14:22
 */
@Data
public class Artist {
    private String name;
    private int age;

    public Artist(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
