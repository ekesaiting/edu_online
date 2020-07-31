package com.feng.servicebase.exception;

import com.feng.servicebase.exception.enums.TeacherExceptionEnum;
import com.feng.servicebase.exception.enums.UserExceptionEnum;

/**
 * @Author shf
 * @Date 2020/7/30 16:59
 */
public class UserException extends BaseException{
    public UserException(UserExceptionEnum exceptionEnum){
        super(exceptionEnum.getMsg());
        setCode(exceptionEnum.getCode());
        setMsg(exceptionEnum.getMsg());
        setType("【后台用户信息异常】");

    }
}
