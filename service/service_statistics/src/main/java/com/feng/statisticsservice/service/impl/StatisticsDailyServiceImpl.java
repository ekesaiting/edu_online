package com.feng.statisticsservice.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.feng.commonutils.Resp;
import com.feng.statisticsservice.client.UcenterClient;
import com.feng.statisticsservice.entity.StatisticsDaily;
import com.feng.statisticsservice.mapper.StatisticsDailyMapper;
import com.feng.statisticsservice.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author shf
 * @since 2020-07-27
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    @Autowired
    private UcenterClient ucenterClient;
    @Override
    @Transactional
    public int registerCount(String day) {

        //先删除表中原有的数据
        QueryWrapper<StatisticsDaily> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("date_calculated",day);
        baseMapper.delete(queryWrapper);

        Resp dailyRegisterResp = ucenterClient.getDailyRegister(day);
        int registerCount = (int)dailyRegisterResp.getData().get("registerCount");
        StatisticsDaily statisticsDaily = new StatisticsDaily();
        statisticsDaily.setRegisterNum(registerCount);
        statisticsDaily.setDateCalculated(day);
        statisticsDaily.setVideoViewNum(RandomUtil.randomInt(100,200));
        statisticsDaily.setLoginNum(RandomUtil.randomInt(100,200));
        statisticsDaily.setCourseNum(RandomUtil.randomInt(100,200));
        return baseMapper.insert(statisticsDaily);
    }

    @Override
    public Map<String, Object> getShowData(String type, String begin, String end) {

        QueryWrapper<StatisticsDaily> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("date_calculated",begin,end);
        queryWrapper.select("date_calculated",type);
        List<StatisticsDaily> staList = baseMapper.selectList(queryWrapper);

        ArrayList<String> dateList = new ArrayList<>();
        ArrayList<Integer> numList = new ArrayList<>();
        for (StatisticsDaily daily : staList) {
            dateList.add(daily.getDateCalculated());
            switch (type) {
                case "register_num":
                    numList.add(daily.getRegisterNum());
                    break;
                case "login_num":
                    numList.add(daily.getLoginNum());
                    break;
                case "video_view_num":
                   numList.add(daily.getVideoViewNum());
                    break;
                case "course_num":
                   numList.add(daily.getCourseNum());
                    break;
                default:
                    break;
            }
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("dateList",dateList);
        map.put("numList",numList);
        return map;
    }
}
