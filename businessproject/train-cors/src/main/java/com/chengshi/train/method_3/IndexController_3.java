package com.chengshi.train.method_3;

import com.chengshi.train.model.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/method3")
@RestController
@CrossOrigin(origins = {"http://localhost:63343","http://localhost:63343"})
@Slf4j
public class IndexController_3 {

    @GetMapping("/ping")
    public String ping (HttpServletRequest request){
        log.info(request.getRequestURI());
        return "ping access";
    }

    @GetMapping("/member")
    public Member member(){
        return new Member("zhangsan","123456",15);
    }
}
