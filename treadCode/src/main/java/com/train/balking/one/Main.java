package com.train.balking.one;

/**
 * @author tjshan
 * @date 2019/11/15 11:03
 */
public class Main {
    public static void main(String[] args) {
        Data data=new Data("balking.txt","empty");
        new ChangeThread("changeThread",data).start();
        new SaveThread("saveThread",data).start();
    }
}
