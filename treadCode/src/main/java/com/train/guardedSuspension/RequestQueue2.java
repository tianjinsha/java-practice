package com.train.guardedSuspension;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author tjshan
 * @date 2019/11/15 14:14
 */
public class RequestQueue2 {
    private final BlockingQueue<Request> queue=new LinkedBlockingDeque<>();
    public synchronized Request getRequest(){
        Request req=null;
        try {
            req=queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return req;
    }

    public synchronized void putRequest(Request request){
        try {
            queue.put(request);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
