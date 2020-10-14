package com.feng.statisticsservice.controller;


import com.feng.commonutils.Resp;
import com.feng.statisticsservice.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author shf
 * @since 2020-07-27
 */
@RestController
@RequestMapping("/staService/sta")
public class StatisticsDailyController {
    @Autowired
    private StatisticsDailyService statisticsService;
    @PostMapping("/registerCount/{day}")
    public Resp registerCount(@PathVariable("day") String day){
        int count=statisticsService.registerCount(day);
        return Resp.ok();
    }
    @GetMapping("/showSta/{type}/{begin}/{end}")
    public Resp showSta(@PathVariable("type") String type,
                        @PathVariable("begin") String begin,
                        @PathVariable("end") String end){
        System.out.println("hello");
        Map<String,Object> map=statisticsService.getShowData(type,begin,end);

        return Resp.ok().data(map);
    }

}

