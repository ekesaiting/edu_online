package com.feng.orderservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.feng.commonutils.Resp;
import com.feng.orderservice.entity.PayLog;
import com.feng.orderservice.service.OrderService;
import com.feng.orderservice.service.PayLogService;
import com.feng.servicebase.exception.enums.OrderExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author shf
 * @since 2020-07-26
 */
@RestController
@RequestMapping("/orderService/payLog")
public class PayLogController {
    @Autowired
    private PayLogService payLogService;

    @PostMapping("/createNative/{orderNo}")
    public Resp createNative(@PathVariable("orderNo") String orderNo){
        Map<String,Object> map=payLogService.createNative(orderNo);
        return Resp.ok().data(map);
    }
    @GetMapping("/getPayStatus/{orderNo}")
    public Resp getPayStatus(@PathVariable("orderNo") String orderNo){
        Map<String,String> map=payLogService.getPayStatus(orderNo);
        if (map==null){
            return Resp.error().message("支付失败");
        }
        if (map.get("trade_state").equals("SUCCESS")){
            payLogService.updateOrderStatus(map);
            return Resp.ok();
        }
        return Resp.ok().message("支付中").code(OrderExceptionEnum.ORDER_PAYING.getCode());
    }


}

