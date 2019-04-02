package com.chengshi.train;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.net.URL;

public class IoUtilTest {

    @Test
    public void  test1() throws IOException {
        InputStream in  = new URL("http://commons.apache.org").openStream();

        try{
            InputStreamReader inR = new InputStreamReader(in);
            BufferedReader buf = new BufferedReader(inR);
            String line;
            while( ( line = buf.readLine() ) != null ){
                System.out.println( line );
            }
        }finally{
            in.close();
        }
    }


    @Test
    public void test2() throws IOException {
        InputStream in = new URL( "http://commons.apache.org" ).openStream();
        try{
            System.out.println( IOUtils.toString( in ) );
        }finally{
            IOUtils.closeQuietly( in );
        }
    }

    @Test
    public  void test3() throws IOException {
        File file = ResourceUtils.getFile("classpath:track1.json");
        InputStream in=new FileInputStream(file);
        String s = IOUtils.toString(in, "utf-8");
        System.out.println(s);
    }

    @Test
    public void test4(){
        try{
        byte[] bytes = new byte[8];
        InputStream is = IOUtils.toInputStream("hello world");
        IOUtils.read(is, bytes);
        System.out.println(new String(bytes));

        bytes = new byte[10];
        is = IOUtils.toInputStream("hello world");
        IOUtils.read(is, bytes, 2, 4);
        System.out.println(new String(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test5(){
        byte[] bytes = new byte[4];
        InputStream is  = IOUtils.toInputStream("hello world");
        try {
            IOUtils.readFully(is,bytes);
            System.out.println(new String(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public  void test6() throws IOException {

//        File file = new File("/Users/jjs/Desktop/abcd.txt");
//        try {
//            OutputStream outputStream = new FileOutputStream(file);
//            String textString = "commons-io工具类";
//            byte[] data = textString.getBytes("UTF-8");
//            outputStream.write(data);
//            outputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
