package com.feng.eduservice.client;

import com.feng.eduservice.client.fallback.OrderDegradeFeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author shf
 * @Date 2020/7/27 17:06
 */
@Component
@FeignClient(name = "service-order", fallback = OrderDegradeFeignClient.class)
public interface OrderClient {
    @GetMapping("/orderService/order/getCoursePayStatus/{courseId}/{memberId}")
    Boolean getCoursePayStatus(@PathVariable("courseId") String courseId,
                               @PathVariable("memberId") String memberId);

}
