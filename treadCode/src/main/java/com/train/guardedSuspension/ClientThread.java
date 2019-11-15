package com.train.guardedSuspension;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author tjshan
 * @date 2019/11/15 14:18
 */
public class ClientThread extends Thread{

    private final Random random;
//    private final RequestQueue requestQueue;
    private final RequestQueue2 requestQueue;

    public ClientThread(String name,RequestQueue2 requestQueue,long seed) {
        super(name);
        this.random = new Random(seed);
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {
        for (int i=0;i<1000;i++){
            Request request=new Request("No "+i);
            System.out.println(Thread.currentThread().getName()+"requests "+request);
            requestQueue.putRequest(request);
            try {
                TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
