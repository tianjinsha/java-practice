package com.train.prac.iotest;

import java.io.*;
import java.net.URL;

/**
 * @author: tjshan
 * @date: 2020-07-04 22:50
 * FileName: Copy
 * Description:
 */
public class FileOperate {

    public  void copy(String origin,String target) throws IOException {
        File o = new File(origin);
        assert o.exists();
        if(o.exists()){
            System.out.println("文件拷贝开始");
            File t = new File(target);
            InputStream inputStream = new FileInputStream(o);
            OutputStream outputStream = new FileOutputStream(t);

            int temp;
            while ((temp = inputStream.read()) !=-1){
                outputStream.write(temp);
            }
            inputStream.close();
            outputStream.close();
            System.out.println("文件拷贝结束");
        }
    }




    public String getClassPath(){
        String  path;
        URL resource = this.getClass().getResource("/");
        path = resource.getPath();
        return path;
    }

}
