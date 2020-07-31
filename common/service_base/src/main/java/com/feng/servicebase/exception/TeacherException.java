package com.feng.servicebase.exception;

import com.feng.servicebase.exception.BaseException;
import com.feng.servicebase.exception.enums.TeacherExceptionEnum;
import lombok.Data;

/**
 * @Author shf
 * @Date 2020/7/8 20:31
 */

public class TeacherException extends BaseException {
    public TeacherException(TeacherExceptionEnum exceptionEnum){
        super(exceptionEnum.getMsg());
        setCode(exceptionEnum.getCode());
        setMsg(exceptionEnum.getMsg());
        setType("【讲师信息异常】");

    }
}
