package com.feng.orderservice.service;

import com.feng.orderservice.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author shf
 * @since 2020-07-26
 */
public interface OrderService extends IService<Order> {

    String createOrder(String courseId, String userId);

}
