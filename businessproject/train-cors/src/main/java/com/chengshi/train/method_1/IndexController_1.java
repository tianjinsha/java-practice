package com.chengshi.train.method_1;

import com.chengshi.train.model.Member;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/method1")
@RestController
public class IndexController_1 {

    @GetMapping("/ping")
    public String ping() {
        return "pring success";
    }

    @GetMapping("/member")
    public Member member() {
        return new Member("zhangsan", "123456", 34);
    }
}
