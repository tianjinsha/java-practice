package com.train.prac;

import com.train.prac.vo.Person;
import org.junit.Test;

import java.io.*;
import java.util.Scanner;

/**
 * @author: tjshan
 * @date: 2020-06-29 21:32
 * FileName: IOTest
 * Description:
 */
public class IOTest {

    @Test
    public void OutStreamTest(){

        String path = this.getClass().getResource("/").getPath();
        String name = "abc.txt";
        String filePath  = path+File.separator+name;
        System.out.println(filePath);
        File file = new File(filePath);
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            String info = "Hello word";
            byte[] data = info.getBytes();
            outputStream.write(data);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void InStreamTest(){
        String path = this.getClass().getResource("/").getPath();
        String name = "abc.txt";
        String filePath  = path+File.separator+name;
        System.out.println(filePath);
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            byte data[] = new byte[1024];
            int len = inputStream.read(data);
            System.out.println(new String(data,0,len));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void InStreamTest2(){
        String path = this.getClass().getResource("/").getPath();
        String name = "abc.txt";
        String filePath  = path+File.separator+name;
        System.out.println(filePath);
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            byte data[] =new byte[1024];
            int len = 0;
            int temp;
            do{
                temp = inputStream.read();
                if(temp !=-1){
                    data[len++] = (byte) temp;
                }

            }while (temp !=-1);
            System.out.println(new String(data,0,len));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void InStreamTest3(){
        String path = this.getClass().getResource("/").getPath();
        String name = "abc.txt";
        String filePath  = path+File.separator+name;
        System.out.println(filePath);
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            byte data[] = new byte[1024];
            int temp;
            int len = 0;
            while ((temp = inputStream.read()) != -1){
                data[len++] = (byte) temp;
            }
            System.out.println(new String(data,0,len));
        }catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void WriterTest(){
        String path = this.getClass().getResource("/").getPath();
        String name = "abc.txt";
        String filePath  = path+File.separator+name;
        System.out.println(filePath);

        try {
            Writer writer = new FileWriter(filePath);
            writer.write("Hello Word !");
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ReaderTest(){
        String path = this.getClass().getResource("/").getPath();
        String name = "abc.txt";
        String filePath  = path+File.separator+name;
        System.out.println(filePath);

        try {
            Reader reader = new FileReader(filePath);
            char data[] = new char[1024];
            int len = reader.read(data);
            System.out.println(new String(data,0,len));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void MemoryStreamTest() throws IOException {
        String info = "Hello Word !";
        InputStream inputStream = new ByteArrayInputStream(info.getBytes());
        OutputStream outputStream = new ByteArrayOutputStream();
        int temp;
        while ((temp = inputStream.read())!=-1){
            outputStream.write(Character.toUpperCase((char)temp));
        }
        String str = outputStream.toString();
        inputStream.close();
        outputStream.close();
        System.out.println(str);
    }


    @Test
    public void PrintStreamTest1() throws FileNotFoundException {
        String path = this.getClass().getResource("/").getPath();
        String name = "abc.txt";
        String filePath  = path+File.separator+name;
        System.out.println(filePath);

        OutputStream outputStream = new FileOutputStream(new File(filePath));
        PrintStream printStream = new PrintStream(outputStream);
        printStream.println(1+ "+" + 1 + "=");
        printStream.println(1+1);
        printStream.println("Hello Word");

    }

    @Test
    public void PrintStreamTest2() throws FileNotFoundException {
        String path = this.getClass().getResource("/").getPath();
        String name = "abc.txt";
        String filePath  = path+File.separator+name;
        System.out.println(filePath);

        OutputStream outputStream = new FileOutputStream(new File(filePath));
        PrintStream printStream = new PrintStream(outputStream);
        int result = 1+1;
        String word = "Hello Word";
        printStream.printf(1+ "+" + 1 + "= %d",result);
        printStream.println();
        printStream.printf("Programmer often say %s",word);
    }


    @Test
    public void SystemOutTest() throws IOException {
        System.out.println("out print");
        System.err.println("error print");
        System.out.write("hello word".getBytes());
    }

    @Test
    public void SystemInTest1() throws IOException {
        InputStream inputStream = System.in;
        byte data[] = new byte[1024];
        System.out.println("请输入内容");
        int len = inputStream.read(data);
        System.out.println("输入的内容是：");
        System.out.println(new String(data,0,len));
    }

    @Test
    public void SystemInTest2() throws IOException {
        InputStream inputStream = System.in;
        StringBuffer buffer = new StringBuffer();
        System.out.println("请输入内容");
        int temp ;

        while ((temp =inputStream.read()) != -1){
            char c = (char) temp;
            if (c == '\n'){
                break;
            }
            buffer.append(c);
        }
        System.out.println("输入的内容是："+buffer);
    }


    @Test
    public void SystemInTest3() throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        System.out.println("请输入内容");
        String info ;
        info  = bufferedReader.readLine();
        System.out.println("输入的内容是："+info);
    }


    @Test
    public void ScannerTest1(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入内容：");
        while (scanner.hasNext()){
            String str = scanner.next();
            System.out.println("输入的内容是："+str);
        }
    }

    @Test
    public void ScannerTest2() throws FileNotFoundException {
        String path = this.getClass().getResource("/").getPath();
        String name = "abc.txt";
        String filePath  = path+File.separator+name;
        System.out.println(filePath);

        FileInputStream fileInputStream = new FileInputStream(new File(filePath));
        Scanner scanner = new Scanner(fileInputStream);
        scanner.useDelimiter("\n");
        while (scanner.hasNext()){
            String str = scanner.next();
            System.out.println(str);
        }
    }

    @Test
    public void SerializeTest1() throws IOException {
        Person person = new Person(20,"tjshan","男","成都师范");
        String path = this.getClass().getResource("/").getPath();
        String name = "person.ini";
        String filePath  = path+File.separator+name;
        System.out.println(filePath);
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(new File(filePath)));
        outputStream.writeObject(person);
        outputStream.close();
    }

    @Test
    public void SerializeTest2() throws IOException, ClassNotFoundException {
        String path = this.getClass().getResource("/").getPath();
        String name = "person.ini";
        String filePath  = path+File.separator+name;
        System.out.println(filePath);

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(new File(filePath)));
        Object object = inputStream.readObject();
        inputStream.close();
        if (object instanceof  Person){
            Person person = (Person) object;
            System.out.println(person);
        }
    }

    @Test
    public void SerializeTest3() throws IOException, ClassNotFoundException {
        Person person = new Person(20,"tjshan","男","成都师范");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(person);
        objectOutputStream.close();


        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Object object = objectInputStream.readObject();
        objectInputStream.close();
        if (object instanceof  Person){
            Person person2 = (Person) object;
            System.out.println(person2);
        }
    }


}
