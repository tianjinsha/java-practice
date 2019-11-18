package com.train.threadpremessage;

import java.util.concurrent.TimeUnit;

/**
 * @author tjshan
 * @date 2019/11/18 14:51
 */
public class Helper {

    public void handle(int count,char c){
        System.out.println("    handle("+count+","+c+") Begin");
        for (int i=0;i<count;i++){
            slowly();
            System.out.println(c);
        }
        System.out.println("    handle("+count+","+c+") End");
    }

    private void slowly() {
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
