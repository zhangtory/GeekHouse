package com.zhangtory.geekhouse.Scheduler;

import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class QuartzManager {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();

    public void initScheduler() throws SchedulerException {
        logger.info("init scheduler...");
        // 从数据库中获取定时任务

        int hour = 17;
        int minute = 21;
        Scheduler scheduler = schedulerFactory.getScheduler();
        String cronExpression = cronPressCreat(minute, hour);
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("ir").withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).build();
        JobDetail jobDetail = JobBuilder.newJob().withIdentity("ir").build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
        scheduler.start();

    }

    private String cronPressCreat(int minute, int hour) {
        StringBuffer sb = new StringBuffer("0 ");
        sb.append(String.valueOf(minute));
        sb.append(" ");
        sb.append(String.valueOf(hour));
        sb.append(" * * ?");
        return sb.toString();
    }

}
