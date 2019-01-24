package com.chengshi.train.method_3;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/method3")
@RestController
public class UserController {

    @CrossOrigin(origins = {"http://localhost:63343","null"})
    @GetMapping("hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/word")
    public String word(){
        return "word";
    }
}
