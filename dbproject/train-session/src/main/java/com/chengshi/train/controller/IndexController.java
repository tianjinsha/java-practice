package com.chengshi.train.controller;

import com.chengshi.train.model.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
public class IndexController {

    @GetMapping("/ping")
    public String ping(){
        return "ping success";
    }

    @GetMapping("/session-string")
    public Object session1(HttpSession session){
        session.setAttribute("session-string","session-string");
        Object attribute = session.getAttribute("session-string");
        log.info("data:"+attribute);
        return attribute;
    }


    @GetMapping("/session-date")
    public Object session2(HttpSession session){
        session.setAttribute("session-date",new Date());
        Object attribute = session.getAttribute("session-date");
        log.info("data:"+attribute);
        return attribute;
    }

    @GetMapping("/session-object/{username}")
    public Object session3(HttpSession session,@PathVariable  String username){
        session.setAttribute("session-object",new Member(username,22));
        Object attribute = session.getAttribute("session-object");
        log.info("data:"+attribute);
        return attribute;
    }

    @GetMapping("/session-list")
    public Object session4(HttpSession session){
        List<Member> list=new ArrayList<>();
        list.add(new Member("tian",22));
        list.add(new Member("jin",32));
        list.add(new Member("shan",42));
        session.setAttribute("session-list",list);
        Object attribute = session.getAttribute("session-list");
        log.info("data:"+attribute);
        return attribute;
    }
}
