package com.train.readwritelock;

/**
 * @author tjshan
 * @date 2019/11/18 13:58
 */
public interface Data {
    char[] read() throws InterruptedException;

    void write(char c) throws InterruptedException;
}
