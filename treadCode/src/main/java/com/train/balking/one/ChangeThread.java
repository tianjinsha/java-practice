package com.train.balking.one;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author tjshan
 * @date 2019/11/15 10:56
 */
public class ChangeThread extends Thread{

    private Data data;
    private final Random random=new Random();

    public ChangeThread(String name,Data data){
        super(name);
        this.data=data;
    }

    @Override
    public void run() {
        try {
            for (int i=0;true;i++){
                data.change("no::"+i);
                TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
                data.save();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
