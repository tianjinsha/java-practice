package com.train.workerthread;

/**
 * @author tjshan
 * @date 2019/11/19 9:17
 */
public class Main {
    public static void main(String[] args) {
        Channel channel=new Channel(5);
        channel.startWorkers();
        new ClientThread("Alice",channel).start();
        new ClientThread("Bobby",channel).start();
        new ClientThread("Chris",channel).start();
    }
}
