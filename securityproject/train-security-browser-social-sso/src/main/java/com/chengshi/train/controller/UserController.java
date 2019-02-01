package com.chengshi.train.controller;

import com.chenghshi.train.model.Member;
import com.chengshi.train.dao.UserRepository;
import com.chengshi.train.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/regist")
    public void regist(User user, HttpServletRequest request) {

        //不管是注册用户还是绑定用户，都会拿到一个用户唯一标识。
        String userId = user.getUsername();
        Member member=new Member();
        member.setUsername("tian");
        member.setPassword("$2a$10$q5Evb.wibLWiroHUA3tj9ekCBBGjkZ8D.wWLebYnYJeGX0D9lbFXu");
        userRepository.insert(member);
        log.info(member.toString());
        log.info(user.getUsername());
        providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
    }

    @GetMapping("/me")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails user) {
        return user;
    }
}
