package com.feng.servicebase.exception;

import com.feng.servicebase.exception.enums.CourseExceptionEnum;
import com.feng.servicebase.exception.enums.SubjectExceptionEnum;

/**
 * @Author shf
 * @Date 2020/7/15 18:24
 */
public class CourseException extends BaseException {
    public CourseException(CourseExceptionEnum exceptionEnum){
        super(exceptionEnum.getMsg());
        setCode(exceptionEnum.getCode());
        setMsg(exceptionEnum.getMsg());
        setType("【课程信息异常】");
    }
}
