package com.train.future;

import java.util.concurrent.TimeUnit;

/**
 * @author tjshan
 * @date 2019/11/22 7:27
 */
public class RealData  implements Data{
    private final String content;
    public RealData(int count, char c){
        System.out.println("    making ReadData("+count+","+c+")Begin");
        char[] buffer=new char[count];
        for(int i=0;i<count;i++){
            buffer[i]=c;
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
            }
        }
        System.out.println("    making ReadData("+count+","+c+")End");
        this.content=new String(buffer);
    }
    @Override
    public String getContent() {
        return content;
    }
}
