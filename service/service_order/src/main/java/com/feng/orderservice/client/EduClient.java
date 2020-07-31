package com.feng.orderservice.client;

import com.feng.commonutils.ordervo.CourseFrontInfoVOOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author shf
 * @Date 2020/7/26 17:21
 */
@Component
@FeignClient(name = "service-edu")
public interface EduClient {
    @GetMapping("/eduService/courseFront/getCourseInfoForOrder/{courseId}")
    CourseFrontInfoVOOrder getCourseInfoForOrder(@PathVariable("courseId")String courseId );
}
