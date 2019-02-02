package com.zhangtory.geekhouse.Scheduler;

import com.zhangtory.geekhouse.Mqtt.MqttServer;
import com.zhangtory.geekhouse.Utils.CMD;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
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
    public void execute(JobExecutionContext jobExecutionContext) {
        Integer opCode = jobExecutionContext.getMergedJobDataMap().getInt("opCode");
        System.out.println(opCode);
        // 通过opcode查找对应的payload

        byte[] payload = {0x02, 0x01, 0x00, 0x00};
        try {
            mqttServer.publish(CMD.IR_COMMAND_TOPIC, payload);
        } catch (MqttException e) {
            e.printStackTrace();
        }


        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("现在的时间是："+ sf.format(date));
    }
}
