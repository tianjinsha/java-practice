package com.train.prac.iotest;

import java.io.*;
import java.util.Scanner;

/**
 * @author: tjshan
 * @date: 2020-07-04 23:12
 * FileName: Main
 * Description:
 */
public class Main {
    public static void main(String[] args) throws IOException {
//        System.out.println("io test");
//        FileOperate fileOperate = new FileOperate();
//        String classPath = fileOperate.getClassPath();
//        String origin = classPath+ File.separator+"abc.txt";
//        String target = classPath+ File.separator+"abc.copy.txt";
//        System.out.println(origin);
//        System.out.println(target);
//        fileOperate.copy(origin,target);


//        =================
//        InputStream inputStream = System.in;
//        StringBuffer buffer = new StringBuffer();
//        System.out.println("请输入内容");
//        int temp ;
//
//        while ((temp =inputStream.read()) != -1){
//            char c = (char) temp;
//            if (c == '\n'){
//                break;
//            }
//            buffer.append(c);
//        }
//        System.out.println("输入的内容是："+buffer);



//        =================
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("请输入内容");
//        String info ;
//        info  = bufferedReader.readLine();
//        System.out.println("输入的内容是："+info);


//        =================
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入内容：");
        if (scanner.hasNext()){
            String str = scanner.next();
            System.out.println("输入的内容是："+str);
        }
    }
}
