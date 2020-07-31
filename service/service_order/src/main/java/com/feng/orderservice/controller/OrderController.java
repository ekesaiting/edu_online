package com.feng.orderservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.feng.commonutils.JwtUtils;
import com.feng.commonutils.Resp;
import com.feng.orderservice.entity.Order;
import com.feng.orderservice.entity.PayLog;
import com.feng.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author shf
 * @since 2020-07-26
 */
@RestController
@RequestMapping("/orderService/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/createOrder/{courseId}")
    public Resp createOrder(@PathVariable("courseId") String courseId,
                            HttpServletRequest servletRequest){
        String userId = JwtUtils.getMemberIdByJwtToken(servletRequest);
        String orderNo=orderService.createOrder(courseId,userId);

        return Resp.ok().data("orderNo",orderNo);
    }
    @GetMapping("getOrderInfo/{orderNo}")
    public Resp getOrderInfo(@PathVariable("orderNo") String orderNo){
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("order_no",orderNo);
        Order order = orderService.getOne(orderQueryWrapper);
        return Resp.ok().data("orderInfo",order);

    }
    @GetMapping("/getCoursePayStatus/{courseId}/{memberId}")
    public Boolean getCoursePayStatus(@PathVariable("courseId") String courseId,
                                   @PathVariable("memberId") String memberId){
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("course_id",courseId)
                .eq("member_id",memberId)
                .eq("status",1);
        int count = orderService.count(orderQueryWrapper);
        return count == 1;


    }

}

