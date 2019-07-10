package com.chengshi.train.trainexception.util;

import lombok.Data;

import java.io.Serializable;

@Data
public class R<T> implements Serializable {
    public static final int NO_LOGIN=-1;

    public static final int SUCCESS=0;

    public static final int FAIL=-1;

    public static final int NO_PERMISSION=2;

    private String msg="success";
    private int code =SUCCESS;

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
