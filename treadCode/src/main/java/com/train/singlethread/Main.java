package com.train.singlethread;

/**
 * @author tjshan
 * @date 2020/1/10 13:48
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("==== test gate ====");
        Gate gate=new Gate();
        new UserThread(gate,"AName","AAdress").start();
        new UserThread(gate,"BName","BAdress").start();
        new UserThread(gate,"CName","CAdress").start();
    }
}
