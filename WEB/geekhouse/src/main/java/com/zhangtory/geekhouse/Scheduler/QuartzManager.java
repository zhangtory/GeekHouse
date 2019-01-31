package com.zhangtory.geekhouse.Scheduler;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class QuartzManager {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();

    public QuartzManager() throws SchedulerException {
        initScheduler();
    }

    public void initScheduler() throws SchedulerException {
        logger.info("init scheduler...");
        // 从数据库中获取定时任务
        addScheduler(1, 17, 53, 1);
    }

    public void addScheduler(int id, int hour, int minute, int opCode) throws SchedulerException {
        String identity = "ir_" + Integer.toString(id);
        String cronExpression = cronPressCreat(minute, hour);

        Scheduler scheduler = schedulerFactory.getScheduler();
        JobDetail jobDetail = JobBuilder.newJob(IRJob.class).withIdentity(identity)
                .usingJobData("opCode", opCode).build();
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(identity)
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).build();
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
