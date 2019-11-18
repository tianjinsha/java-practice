package com.train.threadpremessage;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author tjshan
 * @date 2019/11/18 14:49
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("main Begin");
//        Host host=new Host();
//        Host host=new Host(r -> new Thread(r));
//        Host host=new Host(Executors.defaultThreadFactory());
        Host host=new Host(command -> new Thread(command).start());
        host.request(10,'A');
        host.request(20,'B');
        host.request(30,'C');
        System.out.println("main Begin");

    }
}
