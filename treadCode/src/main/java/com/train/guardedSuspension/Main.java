package com.train.guardedSuspension;

/**
 * @author tjshan
 * @date 2019/11/15 14:24
 */
public class Main {
    public static void main(String[] args) {
//        RequestQueue requestQueue=new RequestQueue();
        RequestQueue2 requestQueue=new RequestQueue2();
        new ClientThread("---send---",requestQueue,31L).start();
        new ServerThread("==receive==",requestQueue,62L).start();
    }
}
