package com.chengshi.train.time;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-17 22:11
 */
//@Component
public class Jobs {
//    fixedRate就是每多次分钟一次，不论你业务执行花费了多少时间。我都是1分钟执行1次，
//    而fixedDelay是当任务执行完毕后1分钟在执行。所以根据实际业务不同，我们会选择不同的方式
    public final static long ONE_Minute =  60 * 1000;
    SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Scheduled(fixedDelay=ONE_Minute)
    public void fixedDelayJob(){
        System.out.println(format.format(new Date())+" >>fixedDelay执行....");
    }

    @Scheduled(fixedRate=ONE_Minute)
    public void fixedRateJob(){
        System.out.println(format.format(new Date())+" >>fixedRate执行....");
    }

   /* *
    *第一位，表示秒，取值0-59
    * 第二位，表示分，取值0-59
    * 第三位，表示小时，取值0-23
    * 第四位，日期天/日，取值1-31
    * 第五位，日期月份，取值1-12
    * 第六位，星期，取值1-7，星期一，星期二...，注：不是第1周，第二周的意思
    另外：1表示星期天，2表示星期一。
    * 第7为，年份，可以留空，取值1970-2099]*/
    @Scheduled(cron="0 15 3 * * ?")
    public void cronJob(){
        System.out.println(format.format(new Date())+" >>cron执行....");
    }

}
