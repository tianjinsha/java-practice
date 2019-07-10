package com.chengshi.train.time;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-17 22:29
 */
public class TimeTask {
    public static void main(String[] args) {
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                System.out.println(format.format(new Date()));
            }
        };
        Timer timer=new Timer();
        //安排指定的任务在指定的时间开始进行重复的固定延迟执行。这里是每3秒执行一次
        timer.schedule(timerTask,10,3000);
    }
}
