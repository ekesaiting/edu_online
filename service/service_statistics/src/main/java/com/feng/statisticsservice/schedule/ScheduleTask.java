package com.feng.statisticsservice.schedule;

import com.feng.statisticsservice.service.StatisticsDailyService;
import com.feng.statisticsservice.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author shf
 * @Date 2020/7/28 17:12
 */
@Component
public class ScheduleTask {
    @Autowired
    private StatisticsDailyService staService;
    //每天凌晨一点自动生成统计数据
    @Scheduled(cron = "0 0 1 * * ?")
    public void regularCreateSta(){
        staService.registerCount(DateUtil.formatDate(DateUtil.addDays(new Date(),-1)));
    }
}
