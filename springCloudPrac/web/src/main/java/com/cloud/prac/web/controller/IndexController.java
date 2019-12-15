package com.cloud.prac.web.controller;

import com.cloud.prac.web.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tjshan
 * @date 2019/9/12 9:06
 */
@RestController
public class IndexController {

    @GetMapping("/ping")
    public String ping(){
        return "ping success";
    }

    @GetMapping("/pro")
    public Object pro(){
        List<Product> list=new ArrayList<>();
        list.add(new Product(1L,"one"));
        list.add(new Product(2L,"two"));
        list.add(new Product(3L,"three"));
        list.add(new Product(4L,"four"));
        return list;
    }

}
