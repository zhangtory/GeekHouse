package com.zhangtory.geekhouse.Configure;

import com.zhangtory.geekhouse.Scheduler.QuartzJobFactory;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 配置SchedulerFactoryBean的jobFactory
 * 使quartz支持spring的@Autowired
 */
@Configuration
public class QuartzConfig {

    @Autowired
    private QuartzJobFactory quartzJobFactory;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobFactory(quartzJobFactory);
        return schedulerFactoryBean;
    }

    @Bean
    public Scheduler scheduler() {
        return schedulerFactoryBean().getScheduler();
    }
}
