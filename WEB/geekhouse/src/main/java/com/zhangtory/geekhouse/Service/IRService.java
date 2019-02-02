package com.zhangtory.geekhouse.Service;

import com.zhangtory.geekhouse.Scheduler.IRJob;
import com.zhangtory.geekhouse.Scheduler.QuartzManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IRService {

    @Autowired
    private QuartzManager quartzManager;

    /**
     * 保存红外任务到数据库
     * @param hour
     * @param minute
     * @param option
     */
    public void addPlan(int hour, int minute, int option) {

    }

    /**
     * 从数据库删除指定任务
     * @param id
     */
    public void deletePlan(int id) {

    }

}
