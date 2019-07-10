package com.grg.train.shiro.controller;

import com.grg.train.shiro.dao.MemberDAO;
import com.grg.train.shiro.dao.MemberRepository;
import com.grg.train.shiro.model.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: tian
 * @date: 2018-12-18 11:35
 */
@RestController
@Slf4j
public class IndexController {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberDAO memberDAO;

    @GetMapping("/ping")
    public String ping(){
        return "ping success !";
    }

    @GetMapping("/test")
    public Object test(){
      Member member=new Member(1,"test","test");
       return member;
    }

    @GetMapping("/{name}")
    public  Object name2(@PathVariable  String name){
        return memberRepository.findByUsername(name);
    }

}
