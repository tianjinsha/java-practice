package com.chengshi.train.exception;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-21 22:29
 */
public class LoginException extends RuntimeException{
    public LoginException(){}

    public LoginException(String msg){
        super(msg);
    }
}
