package com.train.prac;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

public class InputStreamReaderTest {

    private String filePath;

    @Before
    public void init(){
        String path = this.getClass().getResource("/").getPath();
        String name = "abc.txt";
        filePath  = path+ File.separator+name;
        System.out.println(filePath);
    }

    @Test
    public void BufferedReaderTest() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        String str;
        while ((str = bufferedReader.readLine())!=null){
            System.out.println(str);
        }
        bufferedReader.close();
    }

    @Test
    public void BufferedWriterTest() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
        String str = "BufferedWriter write test";
        bufferedWriter.write(str);
        bufferedWriter.newLine();
        bufferedWriter.write(str);
        bufferedWriter.close();
    }

    @Test
    public void BufferedInputStreamTest() throws IOException {
        byte[] bytes = new byte[1024];
        int len;
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(filePath));
        while ((len=bufferedInputStream.read(bytes))!=-1){
            System.out.println(new String(bytes,0,len));
        }
    }

    @Test
    public void BufferedOutputStreamTest() throws IOException {
        String str = "BufferedOutputStream write test";
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filePath,true));
        bufferedOutputStream.write(str.getBytes());
        bufferedOutputStream.write(str.getBytes());
        bufferedOutputStream.close();
    }


    @Test
    public void InputStreamReaderTests() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
        String str;
        while ((str = bufferedReader.readLine())!=null){
            System.out.println(str);
        }
        bufferedReader.close();
    }

    @Test
    public void DataOutputStreamTest() throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(filePath));
        dataOutputStream.writeInt(123456);
        dataOutputStream.writeInt(56789);
        dataOutputStream.close();

        byte[] bytes = new byte[1024];
        int len;
        DataInputStream dataInputStream = new DataInputStream(new FileInputStream(filePath));
//        int i = dataInputStream.readInt();
//        System.out.println(i);
//        while ((len=dataInputStream.readInt())!=-1){
//            System.out.println(len);
//        }
        System.out.println(dataInputStream.readInt());
        System.out.println(dataInputStream.readInt());
        System.out.println(dataInputStream.readInt());
        dataInputStream.close();
    }

}
