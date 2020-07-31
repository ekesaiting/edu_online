package com.feng.statisticsservice.client;

import com.feng.commonutils.Resp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author shf
 * @Date 2020/7/28 16:10
 */
@FeignClient("service-ucenter")
@Component
public interface UcenterClient {
    @GetMapping("/ucenterService/member/getDailyRegister/{day}")
    Resp getDailyRegister(@PathVariable("day") String day);
}
