package com.cehngshi.train.controller;

import com.cehngshi.train.domain.Audience;
import com.cehngshi.train.exception.TrainSecurityException;
import com.cehngshi.train.model.Member;
import com.cehngshi.train.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class controller {
    @Autowired
    private Audience audience;

    @GetMapping("/ping")
    public String ping (){
        return "ping success";
    }

    @GetMapping("/hello")
    public String hello(){
        return "This is hello !";
    }

    @GetMapping("/train")
    public String Train(){
        throw new TrainSecurityException("异常测试");
    }

    @PostMapping(value = "/login",produces = "application/json;charset=UTF-8")
    public String login(@RequestBody Member member) {

        log.info("username"+member.getUsername());
        if (!member.getPassword().equals("password")){
            return "密码错误";
        }

        String jwtToken = JwtUtil.createJwt(member.getUsername(),
               "admin",
                audience.getClientId(),
                audience.getName(),
                audience.getExpiresSecond()*1000,
                audience.getBase64Secret());
        String result_str = "Bearer " + jwtToken;
        return result_str;
    }
}
