package com.zhangtory.geekhouse.Scheduler;

import com.zhangtory.geekhouse.Mqtt.MqttServer;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 红外发射器
 */
@Component
public class IRJob implements Job {

    @Autowired
    private MqttServer mqttServer;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Integer opCode = jobExecutionContext.getMergedJobDataMap().getInt("opCode");
        System.out.println(opCode);

        System.out.println(mqttServer);

        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("现在的时间是："+ sf.format(date));
    }
}
