package com.train.prac;

import com.train.prac.enumeration.Colors;
import com.train.prac.enumeration.Colors2;
import org.junit.Test;

import java.awt.*;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.function.BiConsumer;

public class enumTest {

    @Test
    public void foreachTest(){
        for(Colors c : Colors.values()){
            System.out.println(c.name()+"--"+c.ordinal());
            System.out.println(c);
        }
    }

    @Test
    public void EnumMapTest(){
        Map<Colors, String> enumMap = new EnumMap<>(Colors.class);
        enumMap.put(Colors.RED,"红色");
        enumMap.put(Colors.GREEN,"绿色");
        enumMap.put(Colors.BLUE,"蓝色");

        enumMap.forEach((colors, s) -> {
            System.out.println(colors);
            System.out.println(s);
        });
    }

    @Test
    public void EnumSetTest(){
        EnumSet<Colors> colors = EnumSet.allOf(Colors.class);
        for (Colors c: colors){
            System.out.println(c);
        }
    }

    @Test
    public void EnumInterFaceTest(){
        for (Colors2 c: Colors2.values()){
            System.out.println(c);
            System.out.println(c.getColor());
        }
    }
}
