package com.grg.train.design.single;

/**
 * @author tjshan
 * @description 单例模式-饿汉模式,实例初始化的时候就已经创建好
 * 好处是线程安全，坏处是浪费资源
 *
 * @date  2020-04-06 15:47:38
 */
public class EHan {

    public static EHan instance = new EHan();

    private EHan(){}

    public static EHan getInstance(){
        return instance;
    }
}
