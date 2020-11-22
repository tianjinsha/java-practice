package org.example.control;

import org.example.core.annotation.MethodAnnotation;
import org.example.core.protocol.CommonResult;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tjstj
 * @description TODO
 * @date 2020/11/21 10:30
 */
@RestController
public class IndexController {

    @MethodAnnotation(name = "ping",description = "ping test")
    @RequestMapping("/ping")
    public String ping (){
        System.out.println("ping success");
        return "ping success~";
    }

    @MethodAnnotation(name = "result",description = "result")
    @RequestMapping("/result")
    public CommonResult result(){
        CommonResult result = new CommonResult();
        result.setResult(2);
        return  result;
    }

}
