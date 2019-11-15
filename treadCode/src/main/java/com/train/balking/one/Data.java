package com.train.balking.one;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author tjshan
 * @date 2019/11/15 10:49
 */
public class Data {

    private final String filename;
    private String content;
    private boolean changed;

    public Data(String filename,String content) {
        this.filename = filename;
        this.content=content;
        this.changed=false;
    }

    public synchronized void change(String content){
        this.content=content;
        this.changed=true;
    }

    public synchronized void save() throws IOException {
        if (!changed){
            System.out.println("=====balking=======");
            return;
        }

        doSave();
        this.changed=false;
    }

    private void doSave() throws IOException {

        System.out.println(Thread.currentThread().getName()+"call doSave:: "+content);
        Writer writer=new FileWriter(filename);
        writer.write(content);
        writer.close();
    }
}
