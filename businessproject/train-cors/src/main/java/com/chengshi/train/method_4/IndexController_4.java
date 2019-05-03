package com.chengshi.train.method_4;

import com.chengshi.train.model.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/method4")
@RestController
@Slf4j
public class IndexController_4 {

    @GetMapping("/ping")
    public String ping(HttpServletRequest request) {
        log.info(request.getRequestURI());
        return "ping success";
    }

    @GetMapping("/member")
    public Member member() {
        return new Member("lisi", "123456", 40);
    }
}
