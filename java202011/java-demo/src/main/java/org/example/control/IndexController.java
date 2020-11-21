package org.example.control;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tjstj
 * @description TODO
 * @date 2020/11/21 10:30
 */
@RestController
public class IndexController {

    @RequestMapping("/ping")
    public String ping (){
        return "ping success~";
    }

}
