package com.train.twophase;

import java.util.concurrent.TimeUnit;

/**
 * @author tjshan
 * @date 2019/11/25 1:54
 */
public class CountupThread extends Thread {
    private long counter = 0;
    private volatile boolean shutdownRequest = false;

    public void shutdownRequest() {
        shutdownRequest = true;
        interrupt();
    }

    public boolean isShutdownRequest() {
        return shutdownRequest;
    }


    @Override
    public final void run() {
        try {
            while (!isShutdownRequest()) {
                doWork();
            }
        } catch (InterruptedException e) {

        } finally {
            doShutdown();
        }
    }

    private void doWork() throws InterruptedException {
        counter++;
        System.out.println("doWord:counter=" + counter);
        TimeUnit.MILLISECONDS.sleep(500);
    }

    public void doShutdown() {
        System.out.println("doShutdown:counter=" + counter);
    }
}
