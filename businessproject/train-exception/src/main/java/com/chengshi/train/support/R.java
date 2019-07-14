package com.chengshi.train.support;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-21 22:20
 */
@Data
public class R<T> implements Serializable {

    public static final int NO_LOGIN = 1003;

    public static final int SUCCESS = 1001;

    public static final int FAIL = 1002;

    public static final int NO_PERMISSION = 1004;
    private int code = SUCCESS;
    String msg = "success";

    private T data;

    public R() {
        super();
    }

    public R(T data) {
        super();
        this.data = data;
    }

    public R(T data, String msg) {
        super();
        this.data = data;
        this.msg = msg;
    }

    public R(T data, RCode rCode) {
        super();
        this.data = data;
        this.code = rCode.getCode();
        this.msg = rCode.getMsg();
    }

    public R(Throwable e) {
        super();
        this.msg = e.getMessage();
        this.code = FAIL;
    }
}
