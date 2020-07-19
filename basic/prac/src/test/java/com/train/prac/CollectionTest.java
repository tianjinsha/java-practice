package com.train.prac;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.*;

public class CollectionTest {

    private String filePath;

    @Before
    public void init(){
        String path = this.getClass().getResource("/").getPath();
//        String name = "abc.properties";
        String name = "abc.xml";
        filePath  = path+ File.separator+name;
        System.out.println(filePath);

    }

    @Test
    public void mapTest(){
        Map<String,String> all = new HashMap<>();
        all.put("one","one");
        all.put("two","two");
        all.put("three","three");

        Set<Map.Entry<String, String>> entries = all.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> next = iterator.next();
            System.out.println(next.getKey()+"---"+next.getValue());
        }
    }

    @Test
    public void propertiesTest(){
        Properties properties = new Properties();
        properties.setProperty("one","one");
        properties.setProperty("two","two");
        properties.setProperty("three","three");

        System.out.println(properties.getProperty("one"));
    }

    @Test
    public  void propertiesStoreTest() throws IOException {
        Properties properties = new Properties();
        properties.setProperty("one","one");
        properties.setProperty("two","two");
        properties.setProperty("three","three");

        properties.store(new FileOutputStream(filePath),"number comment");
    }

    @Test
    public  void propertiesStoreTest2() throws IOException {
        Properties properties = new Properties();
        properties.setProperty("one","one");
        properties.setProperty("two","two");
        properties.setProperty("three","three");

        properties.storeToXML(new FileOutputStream(filePath),"number comment");
    }

    @Test
    public  void propertiesLoadTest1() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(filePath));
        System.out.println(properties.getProperty("one"));
    }


    @Test
    public  void propertiesLoadTest2() throws IOException {
        Properties properties = new Properties();
        properties.loadFromXML(new FileInputStream(filePath));
        System.out.println(properties.getProperty("one"));
    }

}
