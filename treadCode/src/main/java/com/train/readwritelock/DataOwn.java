package com.train.readwritelock;

import java.util.concurrent.TimeUnit;

/**
 * @author tjshan
 * @date 2019/11/18 10:35
 */
public class DataOwn implements Data{

    private final char[] buffer;
    private final ReadWriteLock lock=new ReadWriteLock();

    public DataOwn(int size) {
        this.buffer = new char[size];
        for(int i=0;i<buffer.length;i++){
            buffer[i]='*';
        }
    }

    @Override
    public char[] read() throws InterruptedException {
        lock.readLock();
        try {
            return doRead();
        } finally {
            lock.readUnLock();
        }
    }

    @Override
    public void write(char c) throws InterruptedException {
        lock.writeLock();
        try {
            doWrite(c);
        } finally {
            lock.writeUnLock();
        }
    }


    private char[] doRead(){
        char[] newBuf=new char[buffer.length];
        for (int i=0;i<buffer.length;i++){
            newBuf[i]=buffer[i];
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return newBuf;
    }


    private void doWrite(char c){
        for (int i=0;i<buffer.length;i++){
            buffer[i]=c;
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
