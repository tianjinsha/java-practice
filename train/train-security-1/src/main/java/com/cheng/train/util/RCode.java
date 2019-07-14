package com.cheng.train.util;

public enum RCode {
    SUCCESS(1002, "请求成功"),
    REQUESTPWD(1003, "请求失败"),
    UNAUTHORIZED(1004, "未授权"),
    UNLOGIN(1001, "未登陆"),
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
