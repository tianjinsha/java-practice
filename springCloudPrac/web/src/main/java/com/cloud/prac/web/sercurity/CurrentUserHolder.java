package com.cloud.prac.web.sercurity;

/**
 * @author tjshan
 * @date 2019/10/4 12:23
 */
public class CurrentUserHolder {

    private static final ThreadLocal<String> holder=new ThreadLocal<>();

    public  static String get(){
        return holder.get()==null ? "unknown" :holder.get();
    }

    public  static void set(String user){
        holder.set(user);
    }
}
