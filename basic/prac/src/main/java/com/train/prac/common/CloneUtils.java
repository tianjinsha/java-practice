package com.train.prac.common;

import java.io.*;

/**
 * @author: tjshan
 * @date: 2020-06-27 16:57
 * FileName: CloneUtils
 * Description:
 */
public class CloneUtils {

    public static <T extends Serializable> T clone(T obj){
        T cloneObj = null;
        try{
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream obs = new ObjectOutputStream(out);
            obs.writeObject(obj);
            obs.close();

            ByteArrayInputStream ios = new ByteArrayInputStream(out.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(ios);
            cloneObj = (T) ois.readObject();
            ois.close();

        }catch (Exception e){

        }
        return cloneObj;
    }


}
