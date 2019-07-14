package com.grg.train.util;


import com.google.common.base.Optional;

public class Sum {

    public static Integer sum_1(Integer a,Integer b){
        return a+b;
    }

    public static  Integer sum_2(Optional<Integer> a, Optional<Integer> b){
        return a.get()+b.get();
    }
}
