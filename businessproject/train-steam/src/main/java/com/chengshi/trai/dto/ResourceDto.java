package com.chengshi.trai.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ResourceDto implements Serializable {
    private Integer id;
    private Integer pid;
    private String name;
    private Integer sort;
    private List<ResourceDto> children;
}
