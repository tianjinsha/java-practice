package com.grr.tian;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class java8Test {

    public void lamda(){
        MathOperation a= new MathOperation() {
            @Override
            public int operation(int a, int b) {
               return a+b;
            }
        };
        MathOperation b= (a1, b1) -> a1 + b1;

        MathOperation c= (a12, b12) -> {
            a12++;
            return a12 + b12;
        };

        stringOPtion e= new stringOPtion() {
            @Override
            public int opertiona(int a) {
                return 0;
            }

            @Override
            public int opertionb(int a, int b) {
                return 0;
            }
        };
    }

    @Test
    public void testStream() {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
//        for (String s : strings
//        ) {
//            System.out.println("string1:" + s);
//        }
//        System.out.println("--------------------------------");
//        List<String> filtered =
//                strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
//
//        for (String s:filtered
//             ){
//            System.out.println("string2:" + s);
//        }

//        Random random = new Random();
//        random.ints().limit(10).forEach(System.out::println);

        // 获取空字符串的数量
        int count = (int) strings.stream().filter(string -> string.isEmpty()).count();
        Optional<String> first = strings.stream().findFirst();

        System.out.println(first.get());
        System.out.println("count:"+count);
    }

    interface MathOperation {
        int operation(int a, int b);
    }

    interface stringOPtion{
        int opertiona(int a);
        int opertionb(int a,int b);
    }
}
