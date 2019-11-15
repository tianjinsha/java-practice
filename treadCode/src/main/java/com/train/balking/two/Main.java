package com.train.balking.two;

import java.util.concurrent.TimeoutException;

/**
 * @author tjshan
 * @date 2019/11/15 13:41
 */
public class Main {
    public static void main(String[] args) {
        Host host=new Host(1000);
        try {
            System.out.println("execute Begin");
            host.execute();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
