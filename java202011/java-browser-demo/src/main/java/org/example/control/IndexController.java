package org.example.control;

import org.example.core.annotation.MethodAnnotation;
import org.example.core.constant.CommonErrorCodeBase;
import org.example.core.protocol.CommonBean;
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

    @MethodAnnotation(name = "ping")
    @RequestMapping("/ping")
    public CommonResult<CommonBean> ping (){
        System.out.println("ping success");
        CommonResult<CommonBean> result = new CommonResult<>();
        CommonBean commonBean= new CommonBean();
        commonBean.setCode(CommonErrorCodeBase.SUCCESS);
        commonBean.setMessage("ping success");
        result.setParam(commonBean);
        return result;
    }

    @MethodAnnotation(name = "result")
    @RequestMapping("/result")
    public CommonResult<CommonBean> result(){
        CommonResult<CommonBean> result = new CommonResult<>();
        return  result;
    }

}
