package com.train.guardedSuspension;

/**
 * @author tjshan
 * @date 2019/11/15 14:12
 */
public class Request {
    private final String name;

    public Request(String name) {
        this.name = name;
    }

    private String getName(){
        return name;
    }

    @Override
    public String toString() {
        return "[Request "+name+"]";
    }
}
