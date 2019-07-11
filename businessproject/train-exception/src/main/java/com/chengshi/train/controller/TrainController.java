package com.chengshi.train.controller;

import com.chengshi.train.exception.LoginException;
import com.chengshi.train.exception.TrainException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-21 22:15
 */
@Controller
public class TrainController {

    @GetMapping("/train")
    public String train() {
        throw new TrainException("操作异常！");
    }

    @GetMapping("/login")
    public String login() {
        throw new LoginException("登录异常！");
    }
}
