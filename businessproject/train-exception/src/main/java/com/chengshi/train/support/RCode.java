package com.chengshi.train.support;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-21 22:23
 */
public enum RCode {
    SUCCESS(1001, "请求成功"),
    REQUESTPWD(1002, "请求失败"),
    UNlLOGIN(1003, "未登录"),
    UNAUTHORIZED(1004, "未授权");

    private int code;
    private String msg;

    RCode(int code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}
