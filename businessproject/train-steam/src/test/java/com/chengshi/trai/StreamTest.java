package com.chengshi.trai;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class StreamTest {

    public void init(){
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
    }

    @Test
    public void test1(){
        List<String> strings = Arrays.asList("English", "", "China", "Japan", "Franchise","", "USA");
        List<String> collect1 = strings.stream().filter(a -> !strings.isEmpty()).collect(Collectors.toList());
        List<String> collect2 = strings.parallelStream().filter(a -> !strings.isEmpty()).collect(Collectors.toList());
        final List<String> collect3 = strings.stream().filter(s -> !s.isEmpty()).map(s -> {
            if (Character.isUpperCase(s.charAt(0))) {
                return s;
            } else {
                return new StringBuffer().append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
            }
        }).collect(Collectors.toList());


        log.info(collect1.toString());
        log.info(collect2.toString());
        log.info(collect3.toString());
    }

    @Test
    public void test2(){
        Random random=new Random();
//        random.ints().limit(10).forEach(value -> {
//            System.out.println(value+";");
//        });
        random.ints().limit(10).sorted().forEach(System.out::println);
    }

    @Test
    public void test3(){
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // 获取对应的平方数
        List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();

        System.out.println(squaresList.toString());
        System.out.println(stats.getMax());
        System.out.println(stats.getAverage());
        System.out.println(stats.getCount());
        System.out.println(stats.getMin());
    }


    @Test
    public void test4(){
        List<String> items = Arrays.asList("apple", "apple", "banana", "apple", "orange", "banana", "papaya");
        Map<String, Long> result =items.stream().collect(Collectors.groupingBy( Function.identity(), Collectors.counting()));

        Map<String, Long> finalMap = new LinkedHashMap<>();
        result.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue()
                        .reversed()).forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));

        System.out.println(result);
        System.out.println(finalMap);
    }
}
