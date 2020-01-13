package com.train.test20190110;

import java.util.concurrent.TimeUnit;

/**
 * @author tjshan
 * @date 2020/1/10 9:55
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {

        ThreadOther other1 = new ThreadOther("thread-a");
        ThreadOther other2 = new ThreadOther("thread-b");
        Thread threadMian = Thread.currentThread();
        System.out.println(threadMian);
        other1.start();
        synchronized (other1){
                other1.wait();
        }
        other1.join(3000);
//        other2.start();
//        other2.join();

        System.out.println("main thread run");
        for (int i = 0; i < 10; i++) {
            System.out.println("main::" + i);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("main thread end");
    }
}
