package com.chengshi.trai.domain;

import lombok.Data;

@Data
public class Resource {
    private Integer id;
    private Integer pid;
    private Integer sort;
    private String name;


    public Resource() {
    }

    public Resource(Integer id, Integer pid,Integer sort, String name) {
        this.id = id;
        this.pid = pid;
        this.sort=sort;
        this.name = name;
    }
}
