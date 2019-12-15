package com.cloud.prac.web.man;

import ch.qos.logback.core.util.TimeUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author tjshan
 * @date 2019/10/5 19:23
 */
public class thread {
    public static void main(String[] args) {

        A a=new A();
        a.start();

        System.out.println(a.getI());
        if(a.getI()>10){
            a.interrupt();
        }
    }
}

class  A extends Thread{

    private  boolean pleaseStop;
    private int i=0;

    @Override
    public void run() {
        while (!pleaseStop){
            System.out.println(i++);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(i==5){
                System.out.println("this is " +i);
//                this.stop();
                toStop();
            }

        }
    }

    public void toStop(){
        System.out.println("to stop");
        pleaseStop=true;
    }

    public int getI(){
        return this.i;
    }
}
