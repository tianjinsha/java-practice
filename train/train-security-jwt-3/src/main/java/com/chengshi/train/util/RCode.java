package com.chengshi.train.util;

public enum RCode {
    UNLOGIN(1001, "未登陆"),
    SUCCESS(1002, "请求成功"),
    REQUESTPWD(1003, "请求失败"),
    UNAUTHORIZED(1004, "未授权"),
    LOGINSUCCESS(1005,"登录成功"),
    LOGINFAILURE(1005,"登录失败"),
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

    public static RCode getRCode(Integer code){
        RCode defaultCode= RCode.SUCCESS;
        for (RCode rCode:RCode.values()){
            if (rCode.code==code){
                return rCode;
            }
        }
        return defaultCode;
    }

    public static String getMsgByCOde(Integer code){
        return getRCode(code).msg;
    }
}
