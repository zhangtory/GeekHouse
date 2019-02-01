package com.zhangtory.geekhouse.Scheduler;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**
 * quratz在创建job实例时，是quartz在控制，所以spring不能对job中的@Autowired进行自动注入
 * 重写AdaptableJobFactory的createJobInstance方法，并配置jobFactory为QuartzJobFactory
 * 使quartz支持spring的@Autowired
 */
@Component
public class QuartzJobFactory extends AdaptableJobFactory {

    @Autowired
    private AutowireCapableBeanFactory capableBeanFactory;

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object jobInstance = super.createJobInstance(bundle);
        capableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }

}
