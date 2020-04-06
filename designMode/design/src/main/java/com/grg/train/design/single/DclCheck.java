package com.grg.train.design.single;

/**
 * @author tjshan
 * @description 双重校验锁
 *
 *
 * @date  2020-04-06 15:47:38
 */
public class DclCheck {

    /**
     * 防止实例类初始化的时候，出现重排序，导致空指针问题
     */
    public static volatile DclCheck instance;
    private int number;

    private DclCheck(){}

    public static DclCheck getDclCheck(){
        // 其他执行代码
        if (instance == null){
            synchronized (DclCheck.class){
                if (instance == null){
                    return new DclCheck();
                }
            }
        }
        return instance;
    }
}
