package com.train.prac;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

/**
 * @author: tjshan
 * @date: 2020-07-08 22:34
 * FileName: IOTest2
 * Description:
 */
public class IOTest2 {

    private String filePath;

    @Before
    public void init(){
        String path = this.getClass().getResource("/").getPath();
        String name = "abc.txt";
        filePath  = path+ File.separator+name;
        System.out.println(filePath);

    }

    @Test
    public void FileInputStreamTest() throws IOException {
        InputStream fileInputStream = new FileInputStream(new File(filePath));
        byte data[] = new byte[1024];
        int temp;
        int len = 0;
        while ((temp=fileInputStream.read())!=-1){
            data[len++] = (byte) temp;
        }
        System.out.println(new String(data,0,len));
    }

    @Test
    public void FileDescriptorTest(){
        String outText= "FileDescriptor Test";
        PrintStream stream = new PrintStream(new FileOutputStream(FileDescriptor.out));
        stream.println(outText);
        stream.close();
    }
}
