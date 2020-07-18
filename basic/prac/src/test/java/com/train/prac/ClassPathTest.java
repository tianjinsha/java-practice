package com.train.prac;

import org.junit.Test;

import java.io.*;
import java.net.URL;

/**
 * @author: tjshan
 * @date: 2020-06-29 20:52
 * FileName: IoTest
 * Description:
 */
public class ClassPathTest {

    @Test
    public void classPathTest1(){
        URL resource = this.getClass().getResource("");
        String path = resource.getPath();
        // /Users/tjshan/Documents/dev/java-practice/basic/prac/target/test-classes/com/train/prac/
        System.out.println(path);
    }

    @Test
    public void classPathTest2(){
        URL resource = this.getClass().getResource("/");
        String path = resource.getPath();
        // /Users/tjshan/Documents/dev/java-practice/basic/prac/target/test-classes/
        System.out.println(path);
    }

    @Test
    public void classPathTest3(){
        URL resource = Thread.currentThread().getContextClassLoader().getResource("");
        String path = resource.getPath();
        // /Users/tjshan/Documents/dev/java-practice/basic/prac/target/test-classes/
        System.out.println(path);
    }

    @Test
    public void classPathTest4(){
        URL resource = this.getClass().getClassLoader().getResource("");
        String path = resource.getPath();
        // /Users/tjshan/Documents/dev/java-practice/basic/prac/target/test-classes/
        System.out.println(path);
    }

    @Test
    public void classPathTest5(){
        URL resource = ClassLoader.getSystemClassLoader().getResource("");
        String path = resource.getPath();
        // /Users/tjshan/Documents/dev/java-practice/basic/prac/target/test-classes/
        System.out.println(path);
    }

    @Test
    public void classPathTest6() throws IOException {
        File directory = new File("");
        // /Users/tjshan/Documents/dev/java-practice/basic/prac
        System.out.println(directory.getAbsolutePath());
        // /Users/tjshan/Documents/dev/java-practice/basic/prac
        System.out.println(directory.getCanonicalPath());


        URL xmlpath = this.getClass().getClassLoader().getResource("");
        File file = new File(this.getClass().getResource("/").getPath());
        // /Users/tjshan/Documents/dev/java-practice/basic/prac/target/test-classes
        System.out.println(file.getAbsolutePath());
        // /Users/tjshan/Documents/dev/java-practice/basic/prac/target/test-classes
        System.out.println(file.getPath());
    }


    @Test
    public void classPathTest7() throws IOException {
        File directory = new File(this.getClass().getResource("/").getPath());
        String fileName = "abc.txt";
        String filePath  = directory.getAbsolutePath()+File.separator+fileName;
        System.out.println(filePath);

        InputStream inputStream = new FileInputStream(filePath);
        byte[] data = new byte[1024];
        int len = inputStream.read(data);

        System.out.println(new String(data,0,len));
    }


}
