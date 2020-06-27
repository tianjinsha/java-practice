package com.train.prac;

import com.train.prac.vo.Apple;
import com.train.prac.vo.Fruit;
import com.train.prac.vo.Orange;
import org.junit.Test;
import org.mockito.internal.matchers.Or;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: tjshan
 * @date: 2020-06-27 15:31
 * FileName: GenericsTest
 * Description:
 */
public class GenericsTest {

    @Test
    public void pecsTest1(){
        List<? extends Fruit> fruits = new ArrayList<>();
        //fruits.add(new Apple());  //error
        //fruits.add(new Orange()); //error
        //fruits.add(new Fruit());  //error

        Fruit fruit = fruits.get(0);
    }

    @Test
    public void pecsTest2(){
        List<? super Fruit> fruits = new ArrayList<>();
        fruits.add(new Apple());
        fruits.add(new Orange());
        fruits.add(new Fruit());

        Object object = fruits.get(0);
    }

    @Test
    public void pecsTest3(){
        List<? super Orange> fruits = new ArrayList<>();
        // fruits.add(new Apple()); //error
        fruits.add(new Orange());
        //fruits.add(new Fruit());  //error

        Object object = fruits.get(0);
    }

    @Test
    public void pecsTest4(){
        List<? extends Orange> fruits = new ArrayList<>();
        //fruits.add(new Apple());  //error
        //fruits.add(new Orange()); //error
        //fruits.add(new Fruit());  //error

        Orange orange = fruits.get(0);
    }

    @Test
    public void pecsTest5(){
        List<? extends Fruit> fruits1 = new ArrayList<>();
        List<? super Fruit> fruits2 = new ArrayList<>();

        //fruits1.add(new Apple());
        //fruits1.add(new Fruit());
        fruits2.add(new Apple());
        fruits2.add(new Orange());
        fruits2.add(new Fruit());

        Fruit fruit = fruits1.get(0);
        Object object = fruits2.get(0);
    }

    @Test
    public void pecsTest6(){
        List<Fruit> a =new ArrayList<>();
        a.add(new Fruit());
        a.add(new Apple());

        List<Apple> b = new ArrayList<>();
        b.add(new Apple());
        //b.add(new Fruit());

        List<? extends Fruit> fruits = new ArrayList<>();
        fruits = new ArrayList<Fruit>();
        fruits = new ArrayList<Apple>();
        fruits = new ArrayList<Orange>();

        fruits = a;
        //fruits.add(new Fruit()); //error
        //fruits.add(new Apple()); //error
        Fruit fruit = a.get(0);

        fruits = b;
        //fruits.add(new Fruit()); //error
        //fruits.add(new Apple()); //error

        Apple apple = b.get(0);

    }


}
