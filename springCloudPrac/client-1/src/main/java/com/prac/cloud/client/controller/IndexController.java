package com.prac.cloud.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
