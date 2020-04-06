package com.grg.train.design.single;

/**
 * @author tjshan
 * @description 静态内部类
 * 只适合静态域的情况
 * 利用classloder的机制来保证初始化instance时只有一个线程
 *
 * @date  2020-04-06 15:47:38
 */
public class StaticInnerClass {

    private static class SingleHolder{
        private static final StaticInnerClass INSTANCE = new StaticInnerClass();
    }

    private StaticInnerClass(){}

    private static final  StaticInnerClass getInstance(){
        return SingleHolder.INSTANCE;
    }
}
