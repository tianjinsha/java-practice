package com.prac.cloud.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author tjshan
 * @date 2019/5/16 20:27
 */
@RestController
public class ConsumerController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/helloRibbon")
    public String helloConsumer(){

        return restTemplate.getForEntity("http://CLIENT-ONE/hello",String.class).getBody();
    }
}
