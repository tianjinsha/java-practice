package com.chengshi.train.exception;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-21 22:15
 */
public class TrainException extends RuntimeException {
    public TrainException() {
    }

    public TrainException(String msg) {
        super(msg);
    }
}
