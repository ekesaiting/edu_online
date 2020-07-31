package com.feng.servicebase.exception;

import com.feng.servicebase.exception.enums.OrderExceptionEnum;

/**
 * @Author shf
 * @Date 2020/7/27 13:44
 */
public class OrderException extends BaseException {
    public OrderException(OrderExceptionEnum exceptionEnum){
        super(exceptionEnum.getMsg());
        setCode(exceptionEnum.getCode());
        setMsg(exceptionEnum.getMsg());
        setType("【订单异常】");

    }
}
