package com.train.readwritelock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author tjshan
 * @date 2019/11/18 10:35
 */
public class DataLock implements Data{

    private final char[] buffer;
    private final ReadWriteLock lock=new ReentrantReadWriteLock(true);
    private final Lock readLock=lock.readLock();
    private final Lock writeLock=lock.writeLock();
    public DataLock(int size) {
        this.buffer = new char[size];
        for(int i=0;i<buffer.length;i++){
            buffer[i]='*';
        }
    }

    @Override
    public char[] read() {
        readLock.lock();
        try {
            return doRead();
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public void write(char c) {
        writeLock.lock();
        try {
            doWrite(c);
        } finally {
            writeLock.unlock();
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
