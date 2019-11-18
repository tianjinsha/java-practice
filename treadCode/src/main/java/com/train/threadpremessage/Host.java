package com.train.threadpremessage;

import java.util.concurrent.Executor;

/**
 * @author tjshan
 * @date 2019/11/18 14:54
 */
public class Host {
    private final  Helper helper=new Helper();
//    private final ThreadFactory factory;

    private final Executor executor;

    public Host(Executor executor) {
        this.executor = executor;
    }
//    public Host(){}

//    public Host(ThreadFactory factory){
//        this.factory=factory;
//    }



//    public void request(final int count,final char c){
//        System.out.println("    request("+count+","+c+") Begin");
//        new Thread(() -> helper.handle(count,c)).start();
//
//        System.out.println("    request("+count+","+c+") End");
//    }


//    public void request(final int count,final char c){
//        factory.newThread(() -> helper.handle(count,c)).start();
//    }

    public void request(final int count,final char c){
        executor.execute(() -> helper.handle(count,c));
    }

}
