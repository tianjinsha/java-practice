package com.chengshi.train;

import com.alibaba.fastjson.JSON;
import com.chengshi.trai.domain.Track;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Scanner;

@Slf4j
public class ResourceTest {

    @Test
    public void test1(){
        Track track1=new Track("老鼠爱大米",3000);
        String s = JSON.toJSONString(track1);
        System.out.println(s);
    }

    @Test
    public void test2() {

        String file = this.getClass().getClassLoader().getResource("track1.json").getFile();
        String path1 = this.getClass().getClassLoader().getResource("track1.json").getPath();
        String path2 = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        String path3 = this.getClass().getProtectionDomain().getCodeSource().getLocation().getFile();
        System.out.println(file);
        System.out.println(path1);
        System.out.println(path2);
        System.out.println(path3);
    }

    @Test
    public void test3() throws IOException {
        File file=new File("track1.json");
        String canonicalPath = file.getCanonicalPath();
        String absolutePath = file.getAbsolutePath();
        log.info(canonicalPath);
        log.info(absolutePath);
    }

    @Test
    public void  test4() throws IOException {
        File file=new File("src/main/resources/data.json");
        InputStream in=new FileInputStream(file);

        Scanner scanner=new Scanner(in);
        while (scanner.hasNext()){
            System.out.println(scanner.next());
        }
    }

    @Test
    public void test5() throws IOException {
        File file=new File("src/main/resources/data.json");
        byte[] inData = new byte[1024];
        InputStream in=new FileInputStream(file);
        int len=0;
        while ((len=in.read(inData))!=-1){
            System.out.println(new String(inData,0,len));
        }
    }


    @Test
    public void test6() throws IOException {
        File file=new File("src/main/resources/data.json");
        byte[] inData = new byte[1024];
        InputStream in=new FileInputStream(file);
        BufferedInputStream buff = new BufferedInputStream(in);
        int len=0;
        while ((len=buff.read(inData))!=-1){
            System.out.println(new String(inData,0,len));
        }
    }

    @Test
    public void test7() throws IOException {
        InputStream in = this.getClass().getResourceAsStream("/data.json");
        byte[] inData = new byte[1024];
        int len=0;
        while ((len=in.read(inData))!=-1){
            System.out.println(new String(inData,0,len));
        }
    }
    @Test
    public void test8() throws IOException {
        File file=new File("src/main/resources/data.json");
        OutputStream out=new FileOutputStream(file);

    }

    @Test
    public void  test9() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:data.json");
        File absoluteFile = file.getAbsoluteFile();
        System.out.println(absoluteFile);
    }






    //        PrintWriter writer=new PrintWriter(file);
//
//
//
//        OutputStream out=new DataOutputStream(in);
////        OutputStream out=new PrintWriter();
//        byte[] inData = new byte[1024];
//        while (in.read(inData)!=-1){
//
//        }
//        int read = in.read();
}
