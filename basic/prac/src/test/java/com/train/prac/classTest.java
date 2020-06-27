package com.train.prac;

import com.train.prac.vo.Person;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author: tjshan
 * @date: 2020-06-25 16:22
 * FileName: classTest
 * Description:
 */
public class classTest {

    @Test
    public void getNameTest(){
        String str = "Hello";
        Class<? extends String> strClass1 = str.getClass();
        System.out.println(strClass1.getName());

        Class<?> strClass2= String.class;
        System.out.println(strClass2.getName());

        Class<?> strClass3;
        try {
            strClass3 = Class.forName("java.lang.String");
            System.out.println(strClass3.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void instanceTest1() throws Exception {
        Class<?> cls = Class.forName("com.train.prac.vo.Person");
        Person person = (Person) cls.newInstance();
        person.setAge(12);
        person.setName("john");
        person.setAddress("GuangZhou");
        person.setSex("男");
        System.out.println(person.toString());
    }

    @Test
    public void instanceTest2() throws Exception {
        Class<?> cls = Class.forName("com.train.prac.vo.Person");
        Constructor<?>[] constructors = cls.getConstructors();
        Person person = (Person) constructors[1].newInstance(12, "john", "男", "ChengDu");
        System.out.println(person.toString());
    }

    @Test
    public void instanceTest3() throws Exception {
        Class<?> cls = Class.forName("com.train.prac.vo.Person");
        Constructor<?> constructor = cls.getConstructor(Integer.class, String.class, String.class, String.class);
        Person person = (Person) constructor.newInstance(12, "john", "男", "ChengDu");
        System.out.println(person.toString());
    }



    @Test
    public void invokeTest() throws Exception {
        Class<?> cls = Class.forName("com.train.prac.classTest.Calculate");

        Method method = cls.getMethod("say");
        System.out.println("name:"+method.getName());
        int modifiers = method.getModifiers();
        String s = Modifier.toString(modifiers);
        System.out.println("modifiers:"+s);
        System.out.println("returnType:"+method.getReturnType());
        method.invoke(cls.newInstance());

        Method method2 = cls.getMethod("add",Integer.class,Integer.class);
        Object invoke = method2.invoke(cls.newInstance(), 2, 3);
        System.out.println(invoke);
    }

}
