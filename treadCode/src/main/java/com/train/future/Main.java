package com.train.future;

import java.util.concurrent.TimeUnit;

/**
 * @author tjshan
 * @date 2019/11/22 7:01
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("main Begin");
//        Host host=new Host();
        Host2 host=new Host2();
        Data data1=host.request(10,'A');
        Data data2=host.request(20,'B');
        Data data3=host.request(30,'C');

        System.out.println("main otherJob Begin");

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main otherJob End");

        System.out.println("data1="+data1.getContent());
        System.out.println("data2="+data2.getContent());
        System.out.println("data3="+data3.getContent());
        System.out.println("main Begin");
    }
}
