package com.grg.train.design.single;


/**
 * @author tjshan
 * @description 线程安全-加重锁
 *
 *
 * @date  2020-04-06 15:47:38
 */
public class Singleton1 {

    private static Singleton1 instance;

    private Singleton1(){}

    public synchronized  Singleton1 getInstance(){
        if (instance == null){
            return new Singleton1();
        }

        return instance;
    }

}
