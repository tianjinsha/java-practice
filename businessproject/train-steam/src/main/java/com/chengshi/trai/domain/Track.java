package com.chengshi.trai.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 专辑中的一支曲目
 */
@Data
public class Track implements Serializable {
    /**
     * 曲目名称
     */
    private String name;
    /**
     * 时长
     */
    private int length;

    public Track() {
    }

    public Track(String name, int length) {
        this.name = name;
        this.length = length;
    }
}
