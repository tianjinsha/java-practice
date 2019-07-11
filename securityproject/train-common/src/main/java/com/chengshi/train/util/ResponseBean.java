package com.chengshi.train.util;

import lombok.Data;

import java.io.Serializable;

/**
 * 自定义返回信息结构
 * @param <T>
 */
@Data
public class ResponseBean<T> implements Serializable {

    private int code = RCode.SUCCESS.getCode();
    private String msg = RCode.SUCCESS.getMsg();
    private T data;

    public ResponseBean(T data) {
        this.data = data;
    }

    public ResponseBean(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseBean(T data, RCode rCode) {
        this.data = data;
        this.code = rCode.getCode();
        this.msg = rCode.getMsg();
    }

    public ResponseBean(Throwable e) {
        super();
        this.msg = e.getMessage();
        this.code = RCode.REQUESTPWD.getCode();
    }

    public static void main(String[] args) {
        System.out.println(RCode.SUCCESS.getMsg());
    }
}
