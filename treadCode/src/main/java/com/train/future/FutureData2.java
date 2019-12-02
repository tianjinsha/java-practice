package com.train.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author tjshan
 * @date 2019/11/22 8:36
 */
public class FutureData2 extends FutureTask<RealData> implements Data{
    public FutureData2(Callable<RealData> callable) {
        super(callable);
    }

    @Override
    public String getContent() {
        String string=null;
        try {
            string = get().getContent();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return string;
    }
}
