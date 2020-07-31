package com.feng.servicebase.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author shf
 * @Date 2020/7/27 13:42
 */
@Getter
@AllArgsConstructor
public enum OrderExceptionEnum {
    ORDER_PAYING(27000,"订单支付中"),
    ORDER_NOT_EXIST(27001,"订单不存在"),
    ORDER_IS_PAID(27002,"订单已支付");
    private final int code;
    private final String msg;
}
