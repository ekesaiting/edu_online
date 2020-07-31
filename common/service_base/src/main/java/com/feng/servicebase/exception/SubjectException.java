package com.feng.servicebase.exception;

import com.feng.servicebase.exception.enums.SubjectExceptionEnum;

/**
 * @Author shf
 * @Date 2020/7/14 15:28
 */
public class SubjectException  extends BaseException{
    public SubjectException(SubjectExceptionEnum exceptionEnum){
        super(exceptionEnum.getMsg());
        setCode(exceptionEnum.getCode());
        setMsg(exceptionEnum.getMsg());
        setType("【课程分类信息异常】");
    }

}
