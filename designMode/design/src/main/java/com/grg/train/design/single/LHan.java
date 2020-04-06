package com.grg.train.design.single;

/**
 * @author tjshan
 * @description 单例模式-懒汉模式，在实例用到的时候才去创建实例,
 * 如果有则返回，如果没有则创建
 *
 * @date  2020-04-06 15:20:22
 */
public class LHan {
    private static LHan instance;

    private LHan(){}

    public static LHan getInstance(){
        if(instance == null){
            instance = new LHan();
        }
        return  instance;
    }
}
