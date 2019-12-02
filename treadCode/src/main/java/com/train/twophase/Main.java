package com.train.twophase;

import java.util.concurrent.TimeUnit;

/**
 * @author tjshan
 * @date 2019/11/25 1:43
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("main : Begin");
        try {
            CountupThread t = new CountupThread();
            t.start();
            TimeUnit.SECONDS.sleep(10);
            System.out.println("main shutdownRequest");
            t.shutdownRequest();
            System.out.println("main: join");

            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main : End");
    }
}
