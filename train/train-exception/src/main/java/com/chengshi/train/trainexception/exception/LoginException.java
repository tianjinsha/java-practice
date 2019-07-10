package com.chengshi.train.trainexception.exception;

public class LoginException extends RuntimeException{
    public LoginException(){}

    public LoginException(String msg){
        super(msg);
    }
}
