package com.grg.train.design.single;

/**
 * @author tjshan
 * @description 静态库的方式
 *
 *
 * @date  2020-04-06 15:47:38
 */
public class StaticBlock {

    private static  StaticBlock instance;

    private StaticBlock(){}

    static {
        try {
            instance = new StaticBlock();

        }catch (Exception e){

        }
    }


    public static  StaticBlock getInstance(){
        return instance;
    }
}
