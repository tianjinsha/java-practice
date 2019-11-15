package com.train.balking.two;

import java.util.concurrent.TimeoutException;

/**
 * @author tjshan
 * @date 2019/11/15 13:34
 */
public class Host {
    private final long timeout;
    private boolean ready = false;

    public Host(long timeout) {
        this.timeout = timeout;
    }

    public synchronized void setExcutable(boolean on) {
        ready = on;
        notifyAll();
    }

    public synchronized void execute() throws TimeoutException, InterruptedException {
        long start = System.currentTimeMillis();

        while (!ready) {
            long now = System.currentTimeMillis();
            long rest = timeout - (now - start);
            System.out.println(rest);
            if (rest <= 0) {
                throw new TimeoutException("now -start=" + (now - start) + ",timeout =" + timeout);
            }
            wait();
        }
        doExecute();
    }

    private void doExecute() {
        System.out.println(Thread.currentThread().getName() + "call doExecute");
    }
}

