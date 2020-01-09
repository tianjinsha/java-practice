package com.prac.cloud.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tjshan
 * @date 2019/5/15 21:28
 */

@Slf4j
@RestController
public class IndexController {

    @Autowired
    private DiscoveryClient client;

    @RequestMapping("/hello")
    public String index(){
        ServiceInstance instance=client.getLocalServiceInstance();

        log.info("/hello,host:"+instance.getHost()+",service_id:"+instance.getServiceId());
        return "Hello Word";
    }

    @GetMapping("/ping")
    public Object ping(){
        log.info("===this is client1===");
        return "ping success";
    }

    @PostMapping
    public Object add(Integer id,String name){
        log.info("===this is client1===");
        Map<String ,Object> obj=new HashMap<>();
        obj.put("id",id);
        obj.put("name",name);

        log.info(obj.toString());
        return obj;
    }

    @GetMapping("/find/{id}")
    public String findOne(@PathVariable Integer id){
        log.info("===this is client1==="+id);
        return "this is client one !";
    }

}
