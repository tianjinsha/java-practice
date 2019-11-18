package com.train.readwritelock;

/**
 * @author tjshan
 * @date 2019/11/18 10:29
 */
public class Main {
    public static void main(String[] args) {
        Data data=new DataOwn(10);
//        Data data=new DataLock(10);
        System.out.println("System is begin……");
        new ReadThread(data).start();
        new ReadThread(data).start();
        new ReadThread(data).start();
        new ReadThread(data).start();
        new ReadThread(data).start();
        new ReadThread(data).start();

        new WriterThread(data,"ACDEFGHIJKLMNOPQRSTUVWZYZ").start();
        new WriterThread(data,"acdefghijklmnopqrstuvwzyz").start();
    }
}
