package com.chengshi.train.config;

import com.chengshi.train.quartz.QuartzTask;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-17 22:48
 */
@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail jobDetail(){
        return JobBuilder.newJob(QuartzTask.class).withIdentity("quartzTask").storeDurably().build();
    }

    @Bean
    public Trigger trigger(){
        SimpleScheduleBuilder scheduleBuilder=SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5) //设置时间周期单位秒
                .repeatForever();

        return TriggerBuilder.newTrigger().forJob(jobDetail())
                .withIdentity("quartzTask")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
