package com.chengshi.train.trainjwt2.controller;

import com.chengshi.train.trainjwt2.model.Member;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.util.Date;

@RestController
public class IndexController {
    @GetMapping("/ping")
    public String ping(){
        return "ping success !";
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello hello hello !";
    }

    @PostMapping("login")
    public Object login(@RequestBody Member member) throws ServletException {
        // Check if username and password is null
        if (member.getUsername() == "" || member.getUsername() == null
                || member.getPassword() == "" || member.getPassword() == null)
            throw new ServletException("Please fill in username and password");

        // Check if the username is used
        if(!member.getUsername().equals("username") || !member.getPassword().equals("password")){
            throw new ServletException("Please fill in username and password");
        }

        // Create Twt token
        String jwtToken = Jwts.builder().setSubject(member.getUsername()).claim("roles", "member").setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact();

        return "Bearer "+jwtToken;
    }
}
