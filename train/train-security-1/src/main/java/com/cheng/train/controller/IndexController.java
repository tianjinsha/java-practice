package com.cheng.train.controller;

import com.cheng.train.util.ResponseBean;
import com.cheng.train.util.properties.SecurityProperties;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class IndexController {

    @Autowired
    SecurityProperties securityProperties;

    @GetMapping("ping")
    public String ping(){
        return "ping success !";
    }

    @GetMapping("/admin")
    public String admin(){
        return "This is admin";
    }

    @GetMapping("/property")
    public String property(){
        return securityProperties.getLoginType();
    }
    @GetMapping("/back")
    public String back(){
        return "This is back";
    }

    @GetMapping("/body")
    public ResponseBean body(){
        return new ResponseBean("1003","访问的服务需要身份认证，请引导用户到登录页");
    }


    public static void main(String[] args) {

        @Data
        class Member{
            private String name;
            private int age;

            public Member(String name, int age) {
                this.name = name;
                this.age = age;
            }
        }

        List<Member> list=new ArrayList();
        list.add(new Member("蔡敏敏",32));
        list.add(new Member("颜芳艳",22));
        list.add(new Member("颜芳艳",24));
        list.add(new Member("颜芳艳",29));
        list.add(new Member("宾本诚,",52));
        list.add(new Member("宾本诚",32));

        Map<String ,Integer> avgs=new HashMap<>();
        list.forEach((key->{

        }));




        Map<String, Member> collect = list.stream().collect(Collectors.toMap(Member::getName, member -> member));

        collect.forEach((key,value)->{
            System.out.println(key+"::"+value.toString());
        });
    }
}
