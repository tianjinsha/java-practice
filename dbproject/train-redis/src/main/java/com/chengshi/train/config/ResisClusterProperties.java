package com.chengshi.train.config;

import lombok.Data;

import java.util.List;

@Data
public class ResisClusterProperties {
    private List<String> nodes;
    private Integer maxRedirects;
}
