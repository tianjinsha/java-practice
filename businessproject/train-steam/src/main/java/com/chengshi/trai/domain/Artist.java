package com.chengshi.trai.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
/**
 * 创作音乐的个人或团队
 */
public class Artist implements Serializable {
    /**
     * 艺术家的名称
     */
    private String name;
    /**
     *乐队成员
     */
    private List<String> members;
    /**
     * 乐队来自哪里
     */
    private String origin;


    public Artist() {
    }

    public Artist(String name, List<String> members, String origin) {
        this.name = name;
        this.members = members;
        this.origin = origin;
    }
}
