package com.cloud.prac.web.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tjshan
 * @date 2020/1/9 10:09
 */
@FeignClient("client-one")
public interface FeignService {

    @GetMapping("/find/{id}")
    String find(@PathVariable("id") Integer id);

    @RequestMapping("/ping")
    String ping();
}
