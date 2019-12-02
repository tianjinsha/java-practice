package com.train.future;

import java.util.concurrent.Callable;

/**
 * @author tjshan
 * @date 2019/11/22 8:34
 */
public class Host2 {
    public Data request(final int count,final char c){
        System.out.println("    request ReadData("+count+","+c+")Begin");

        FutureData2 future=new FutureData2(new Callable() {
            @Override
            public Object call() throws Exception {
                return new RealData(count,c);
            }
        });
        new Thread(future).start();
        System.out.println("    request ReadData("+count+","+c+")End");
        return future;
    }
}
