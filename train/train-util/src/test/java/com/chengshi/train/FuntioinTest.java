package com.chengshi.train;

import org.junit.Test;

import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-20 11:10
 */
public class FuntioinTest {
    public final static ThreadLocal<DateFormatter> formatter =
            ThreadLocal.withInitial(() -> new DateFormatter(new SimpleDateFormat("dd-MMM-yyyy")));

    @Test
    public void PredicationTest() {
        Predicate<Integer> larger5 = x -> x > 5;
        Predicate<Integer> small10 = x -> x < 10;
        Predicate<Integer> small1 = x -> x < 1;

        Predicate<Integer> between50 = larger5.and(small10);

        Predicate<Integer> integerOr = larger5.or(small1);

        System.out.println(">5:" + larger5.test(7));
        System.out.println("5-10:" + between50.test(8));
        System.out.println(">5 || <1:" + integerOr.test(0));
    }

    @Test
    public void ConsumerTest() {
        Consumer<Integer> consumer = x -> {
            int a = x + 2;
            System.out.println(a);
            System.out.println(a + 2);
        };
        consumer.accept(5);
    }

    @Test
    public void BinaryOperator() {
        BinaryOperator<Integer> binaryOperator = (x, y) -> x + y;
        System.out.println(binaryOperator.apply(1, 3));
    }

    @Test
    public void FunctionTest() {
        //compose 和 andThen 的不同之处是函数执行的顺序不同。compose 函数先执行参数，然后执行调用者，
        // 而 andThen 先执行调用者，然后再执行参数
        Function<Integer, Integer> name = e -> e * 2;
        Function<Integer, Integer> square = e -> e * e;
        int value = name.andThen(square).apply(3);
        System.out.println("andThen value=" + value);
        int value2 = name.compose(square).apply(3);
        System.out.println("compose value2=" + value2);
        //返回一个执行了apply()方法之后只会返回输入参数的函数对象
        Object identity = Function.identity().apply("huohuo");
        System.out.println(identity);
    }

    @Test
    public void Stream(){
        List<Artist> artists=new ArrayList<>();
        artists.add(new Artist("大哥",34));
        artists.add(new Artist("二哥",52));
        artists.add(new Artist("五弟",27));
        artists.add(new Artist("五弟",27));
        artists.add(new Artist("小九",33));
        artists.add(new Artist("八妹",23));
        artists.add(new Artist("三吧",21));

        long count = artists.stream().filter(new Predicate<Artist>() {
            @Override
            public boolean test(Artist artist) {
                return artist.getAge() > 25;
            }
        }).count();

        artists.stream().filter(artist -> artist.getAge() > 25).count();
//        artists.stream().filter(artist ->{
//            System.out.println("name:"+artist.getName());
//           return artist.getAge() > 25;
//        }).count();
        artists.stream().filter(artist ->{
            System.out.println("name:"+artist.getName());
            return artist.getAge() > 25;
        }).distinct().count();

        System.out.println("count::"+count);
    }

    @Test
    public void ArrayTest(){
        Integer[] a={1,3,6,7,11,3};
        List<Integer> integers = Arrays.asList(a);

        integers.forEach(y->{
            y=y+1;
            System.out.println("integers::"+y.intValue());
        });

        List<Integer> collect = Stream.of(a).filter(x->x>3).collect(Collectors.toList());
        for (Integer i:collect){
            System.out.println(i);
        }
    }

    @Test
    public void MapTest(){
        String [] a={"a","b","ee","dd"};
        List<String> list = Arrays.asList(a);

        List<String> collect = Stream.of(a).map(string -> string.toUpperCase()).collect(Collectors.toList());
        collect.forEach(x-> System.out.println("x::"+x));
    }

    @Test
    public void FlatMapTest(){
        String [] a={"a","b","e","d"};
        String [] b={"gg","ee","hh","zz"};

        List<String> collect = Stream.of(Arrays.asList(a), Arrays.asList(b))
                .flatMap(strings -> strings.stream())
                .collect(Collectors.toList());

        collect.forEach(x-> System.out.println("x::"+x));

    }

    @Test
    public void testMax(){
        List<Artist> artists=new ArrayList<>();
        artists.add(new Artist("大哥",34));
        artists.add(new Artist("二dfsf哥",52));
        artists.add(new Artist("五弟rewe",27));
        artists.add(new Artist("五弟",27));
        artists.add(new Artist("小sf九",33));
        artists.add(new Artist("八sdf妹",23));
        artists.add(new Artist("三",21));

        Artist artist = artists.stream().max(Comparator.comparing(track -> track.getAge())).get();

        System.out.println("artist::"+artist);
    }


}
