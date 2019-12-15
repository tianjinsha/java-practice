package com.cloud.prac.web.model;

import lombok.Data;

/**
 * @author tjshan
 * @date 2019/10/4 12:19
 */
@Data
public class Product {

    private Long id;
    private String name;

    public Product() {
    }

    public Product(Long id,String name) {
        this.id=id;
        this.name = name;
    }
}
