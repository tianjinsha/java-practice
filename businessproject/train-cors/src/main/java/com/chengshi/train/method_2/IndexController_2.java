package com.chengshi.train.method_2;

import com.chengshi.train.model.Member;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/method2")
@RestController
public class IndexController_2 {

    @GetMapping("/ping")
    public String ping(){
        return "pring success";
    }

    @GetMapping("/member")
        public Member member(){
            return new Member("zhangsan","123456",15);
    }
}
