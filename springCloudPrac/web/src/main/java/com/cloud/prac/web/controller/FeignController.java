package com.cloud.prac.web.controller;

import com.cloud.prac.web.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tjshan
 * @date 2020/1/9 10:13
 */
@RestController
public class FeignController {

    @Autowired
    FeignService feignService;

    @GetMapping("/findConsumer")
    public String findConsumer(){
        return  feignService.find(123);
    }

    @GetMapping("/pingConsumer")
    public String pingConsumer(){
        return  feignService.ping();
    }
}
