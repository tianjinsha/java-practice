package com.prac.cloud.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tjshan
 * @date 2019/5/16 20:27
 */
@RestController
@Slf4j
public class ConsumerController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/helloRibbon")
    public String helloConsumer(){

        return restTemplate.getForEntity("http://CLIENT-ONE/hello",String.class).getBody();
    }


    @PostMapping("/add")
    public Object add(Integer id,String name){
        log.info("===this is client2===");
        Map<String ,Object> obj=new HashMap<>();
        obj.put("id",id);
        obj.put("name",name);

        log.info(obj.toString());
        return obj;
    }

    @GetMapping("/find/{id}")
    public String findOne(@PathVariable Integer id){
        log.info("===this is client2==="+id);
        return "this is client two !";
    }

    @GetMapping("/ping")
    public String ping(){
        log.info("===this is client2===");
        return "ping success";
    }
}
