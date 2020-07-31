package com.feng.orderservice.client;

import com.feng.commonutils.ordervo.UcenterMemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author shf
 * @Date 2020/7/26 17:24
 */
@Component
@FeignClient(name = "service-ucenter")
public interface UcenterClient {
    @GetMapping("/ucenterService/member/getMemberInfoForOrder/{memberId}")
    UcenterMemberOrder getMemberInfoForOrder(@PathVariable("memberId") String memberId);
}
