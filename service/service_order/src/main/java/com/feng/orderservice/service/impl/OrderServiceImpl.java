package com.feng.orderservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.feng.commonutils.ordervo.CourseFrontInfoVOOrder;
import com.feng.commonutils.ordervo.UcenterMemberOrder;
import com.feng.orderservice.client.EduClient;
import com.feng.orderservice.client.UcenterClient;
import com.feng.orderservice.entity.Order;
import com.feng.orderservice.mapper.OrderMapper;
import com.feng.orderservice.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feng.orderservice.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author shf
 * @since 2020-07-26
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private EduClient eduClient;
    @Autowired
    private UcenterClient ucenterClient;
    @Override
    public String createOrder(String courseId, String userId) {
        UcenterMemberOrder member = ucenterClient.getMemberInfoForOrder(userId);
        CourseFrontInfoVOOrder course = eduClient.getCourseInfoForOrder(courseId);

        Order order = new Order();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(course.getId());
        order.setCourseTitle(course.getTitle());
        order.setCourseCover(course.getCover());

        order.setTeacherName(course.getTeacherName());
        order.setMemberId(member.getId());
        order.setNickname(member.getNickname());
        order.setMobile(member.getMobile());
        order.setTotalFee(course.getPrice());

        order.setPayType(1);
        order.setStatus(0);
        baseMapper.insert(order);

        return order.getOrderNo();
    }

}
