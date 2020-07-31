package com.feng.servicebase.exception;

import com.feng.servicebase.exception.enums.MemberExceptionEnum;

/**
 * @Author shf
 * @Date 2020/7/22 22:12
 */
public class MemberException extends BaseException {
    public MemberException(MemberExceptionEnum exceptionEnum){
        super(exceptionEnum.getMsg());
        setCode(exceptionEnum.getCode());
        setMsg(exceptionEnum.getMsg());
        setType("【用户信息异常】");

    }
}
