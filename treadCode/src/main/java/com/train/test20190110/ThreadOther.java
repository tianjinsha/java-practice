package com.train.test20190110;

import java.util.concurrent.TimeUnit;

/**
 * @author tjshan
 * @date 2020/1/10 9:56
 */
public class ThreadOther extends Thread{
    private String name;
    ThreadOther(){}
    ThreadOther(String name){
        super(name);
    }

    @Override
    public void run() {
        System.out.println("other thread start");
        for (int i=0;i<10;i++){
            System.out.println(Thread.holdsLock(this));
            System.out.println("other::"+Thread.currentThread().getName()+"::"+i);
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("other thread end");

    }
}
