package com.chengshi.train;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

public class StreamTest1 {

    @Test
    public void test(){
        double[] arry={0,1,2,3,4,3.5};
        Arrays.parallelPrefix(arry,Double::sum);
//        Arrays.parallelPrefix(arry,Double::max);
        for (double v : arry) {
            System.out.println(v);
        }
        System.out.println("--------------");
        int start=2;
        double[] doubles = IntStream.range(3, arry.length).mapToDouble(i -> {
            double prefix = i == start ? 0 : arry[i - 3];
            return (arry[i] - prefix) / 3;
        }).toArray();

        for (double v : doubles) {
            System.out.println(v);
        }
    }

    @Test
    public void test2(){
        double[] values=new double[6];
        Arrays.parallelSetAll(values,i->i*i);
        for (double value : values) {
            System.out.println(value);
        }
    }

    @Test
    public void test3(){
        Integer arr[]={1,2,3,4,5,6};
        int binarySearch = Arrays.binarySearch(arr, 3);
        System.out.println(binarySearch);
    }

    @Test
    public void test4(){
        IntStream.rangeClosed(13, 15).forEach(s->System.out.print(s +" "));
        System.out.println("\n--Using IntStream.range--");
    }
}
