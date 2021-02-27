package org.example.control;

import org.example.core.annotation.MethodAnnotation;
import org.example.core.constant.CommonErrorCodeBase;
import org.example.core.protocol.CommonBean;
import org.example.core.protocol.CommonResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tjstj
 * @description TODO
 * @date 2020/11/21 10:30
 */
@RestController
public class IndexController {

    @MethodAnnotation(name = "ping")
    @RequestMapping("/ping")
    public CommonResult ping (){
        System.out.println("ping success");
        CommonResult result = new CommonResult();
        CommonBean commonBean= new CommonBean();
        commonBean.setCode(CommonErrorCodeBase.SUCCESS);
        commonBean.setMessage("ping success");
        result.setParam(commonBean);
        return result;
    }

    @MethodAnnotation(name = "result")
    @RequestMapping("/result")
    public CommonResult result(){
        CommonResult result = new CommonResult();
        return  result;
    }

    @GetMapping("/me")
    public Object me1(Authentication authentication){
        return authentication.getPrincipal();
    }
    @GetMapping("/me2")
    public Object me2(Authentication authentication){
        return authentication.getDetails();
    }
    @GetMapping("/me3")
    public Object me3(Authentication authentication){
        return authentication;
    }


    @GetMapping("/me4")
    public Object me4(@AuthenticationPrincipal UserDetails user){
        return user;
    }

}
