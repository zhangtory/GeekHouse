package com.zhangtory.geekhouse.Scheduler;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 定时任务管理器
 * 启动时初始化数据库中存储的任务，程序运行中对指定任务进行修改删除等操作。
 */
@Component
public class QuartzManager implements ApplicationRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Scheduler scheduler;

    /**
     * 初始化数据库中的定时任务
     * @throws SchedulerException
     */
    public void initScheduler() throws SchedulerException {
        logger.info("init scheduler...");
        // 从数据库中获取定时任务
        addScheduler("ir_1", 17, 26, 1);
    }

    /**
     * 增加定时任务
     * @param identity 前缀_任务id，例如:"ir_1"
     * @param hour
     * @param minute
     * @param opCode 操作码id
     * @throws SchedulerException
     */
    public void addScheduler(String identity, int hour, int minute, int opCode) throws SchedulerException {
        String cronExpression = cronPressCreat(minute, hour);

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

    /**
     * 在springboot启动完成后，初始化定时任务
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        initScheduler();
    }
}
