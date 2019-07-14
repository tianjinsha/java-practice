package com.cehngshi.train.util;

public enum RCode {
    SUCCESS(0, "请求成功"),
    REQUESTPWD(1, "请求失败"),
    UNAUTHORIZED(-1, "未授权"),
    ADDFAIL(-2,"新建失败"),
    READDFAIL(-3,"记录已存在，无法添加");

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
