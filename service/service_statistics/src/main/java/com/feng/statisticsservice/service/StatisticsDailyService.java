package com.feng.statisticsservice.service;

import com.feng.statisticsservice.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author shf
 * @since 2020-07-27
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    int registerCount(String day);

    Map<String, Object> getShowData(String type, String begin, String end);
}
