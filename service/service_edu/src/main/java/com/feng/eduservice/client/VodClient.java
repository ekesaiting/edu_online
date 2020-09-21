package com.feng.eduservice.client;

import com.feng.commonutils.Resp;
import com.feng.eduservice.client.fallback.VodFileDegradeFeignClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author shf
 * @Date 2020/7/20 17:20
 */
@Component
@FeignClient(name = "service-vod", fallback = VodFileDegradeFeignClient.class)
public interface VodClient {
    @DeleteMapping("/vodService/deleteVod/{vodId}")
    Resp deleteById(@PathVariable("vodId") String vodId);

    @DeleteMapping("vodService/deleteVodBatch")
    //调用时形参名要与RequestParam注解中的一致
    Resp deleteByIdBatch(@RequestParam("vodIdList") List<String> vodIdList);
}
