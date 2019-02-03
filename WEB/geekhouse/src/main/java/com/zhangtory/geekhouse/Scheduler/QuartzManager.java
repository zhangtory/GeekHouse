package com.zhangtory.geekhouse.Scheduler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangtory.geekhouse.Scheduler.Job.IRJob;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
    private void initScheduler() throws SchedulerException, ClassNotFoundException, IOException {
        logger.info("init scheduler...");
        // 从数据库中获取定时任务

        String nameIdentity = String.valueOf(3L);
        String groupIdentity = "ir";
        String cronString = cronPressCreat(10,14);

        String classname = IRJob.class.getName();
        Class cls = Class.forName(classname);

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("opCode", 1);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(dataMap);
        Map<String, Object> reMap = objectMapper.readValue(json, HashMap.class);

        addScheduler(nameIdentity, groupIdentity, cronString, cls, reMap);

        scheduler.start();
    }

    /**
     * 增加定时任务
     * @param nameIdentity
     * @param groupIdentity 任务组名
     * @param cronExpression cron表达式
     * @param jobClass 任务执行的类
     * @param dataMap 其他参数
     * @throws SchedulerException
     */
    public void addScheduler(String nameIdentity, String groupIdentity, String cronExpression,
                             Class jobClass, Map<String, Object> dataMap) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(nameIdentity, groupIdentity).build();
        if (dataMap != null) {
            jobDetail.getJobDataMap().putAll(dataMap);
        }
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(nameIdentity, groupIdentity)
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }

    /**
     * 删除定时任务
     * @param nameIdentity
     * @param groupIdentity
     */
    public void deleteScheduler(String nameIdentity, String groupIdentity) {

    }

    /**
     * 修改定时任务
     * @param nameIdentity
     * @param groupIdentity
     * @param cronExpression
     * @param jobClass
     * @param dataMap
     */
    public void modifiedScheduler(String nameIdentity, String groupIdentity, String cronExpression,
                                  Class jobClass, Map<String, Object> dataMap) {

    }

    private String cronPressCreat(int hour, int minute) {
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
//        initScheduler();
    }
}
