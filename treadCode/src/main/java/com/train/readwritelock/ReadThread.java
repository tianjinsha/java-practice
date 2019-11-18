package com.train.readwritelock;

/**
 * @author tjshan
 * @date 2019/11/18 10:53
 */
public class ReadThread extends Thread{

    private final Data data;

    public ReadThread(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        try {
            while (true){
                char[] readBuf=data.read();
                System.out.println(Thread.currentThread().getName()+"reads "+String.valueOf(readBuf));
            }
        } catch (Exception e) {

        }
    }
}
