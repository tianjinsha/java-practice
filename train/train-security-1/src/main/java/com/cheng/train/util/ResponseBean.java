package com.cheng.train.util;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseBean<T> implements Serializable {
    public static final int NO_LOGIN=10001;

    public static final int SUCCESS=1002;

    public static final int FAIL=1003;

    public static final int NO_PERMISSION=10004;

    private String msg="success";
    private int code =SUCCESS;

    private T data;
    public ResponseBean() {
        super();
    }

    public ResponseBean(T data) {
        super();
        this.data = data;
    }

    public ResponseBean(T data, String msg) {
        super();
        this.data = data;
        this.msg = msg;
    }
    public ResponseBean(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public ResponseBean(T data, RCode rCode) {
        super();
        this.data = data;
        this.code = rCode.getCode();
        this.msg = rCode.getMsg();
    }

    public ResponseBean(Throwable e) {
        super();
        this.msg = e.getMessage();
        this.code = FAIL;
    }
}
