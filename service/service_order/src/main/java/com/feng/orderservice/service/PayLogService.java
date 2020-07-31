package com.feng.orderservice.service;

import com.feng.orderservice.entity.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author shf
 * @since 2020-07-26
 */
public interface PayLogService extends IService<PayLog> {

    Map<String, Object> createNative(String orderNo);

    Map<String, String> getPayStatus(String orderNo);

    void updateOrderStatus(Map<String, String> map);
}
