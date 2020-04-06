package com.grg.train.design.single;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        Thread  thread1 = new newThread("thread1");
        Thread  thread2 = new newThread("thread2");
        thread1.start();
        thread2.start();

    }


    static class  newThread extends Thread{

        newThread(String name){
            super(name);
        }

        @Override
        public void run() {
            for (int i=0;i<100;i++){
                LHan instance = LHan.getInstance();
                System.out.println(Thread.currentThread().getName()+"::"+instance.hashCode());
                try {
                    TimeUnit.MICROSECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
