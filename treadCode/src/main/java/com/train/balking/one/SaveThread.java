package com.train.balking.one;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author tjshan
 * @date 2019/11/15 10:58
 */
public class SaveThread  extends Thread{
    private Data data;

    public SaveThread(String name,Data data){
        super(name);
        this.data=data;
    }

    @Override
    public void run() {
        try {
            while (true){
                data.save();
                TimeUnit.MILLISECONDS.sleep(1000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
