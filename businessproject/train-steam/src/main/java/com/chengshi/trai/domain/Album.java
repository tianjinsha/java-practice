package com.chengshi.trai.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 专辑 由若干曲目组层
 */
@Data
public class Album implements Serializable {
    /**
     * 专辑名
     */
    private String name;
    /**
     * 专辑上所有曲目的列表
     */
    private List<Track> tracks;
    /**
     * 专辑上所有参与的艺术家列表
     */
    private List<Artist> musicians;
}
