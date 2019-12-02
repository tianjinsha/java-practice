package com.train.future;

/**
 * @author tjshan
 * @date 2019/11/22 7:38
 */
public class Host {
    public Data request(final int count,final char c){
        System.out.println("    request ReadData("+count+","+c+")Begin");
        final FutureData future =new FutureData();
        new Thread(){
            @Override
            public void run() {
                RealData realData=new RealData(count,c);
                future.setRealData(realData);
            }
        }.start();
        System.out.println("    request ReadData("+count+","+c+")End");
        return future;
    }
}
