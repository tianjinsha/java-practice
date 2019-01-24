package com.chengshi.train.util;

public enum RCode {
    SUCCESS(1000, "请求成功"),
    UNLOGIN(1001, "未登陆"),
    REQUESTPWD(1002, "请求失败"),
    UNAUTHORIZED(1003, "未授权"),
    LOGINSUCCESS(1005,"登录成功"),
    LOGINFAILURE(1006,"登录失败"),

    ADDFAIL(1021,"新建失败"),
    READDFAIL(1022,"记录已存在，无法添加");

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
