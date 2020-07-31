package com.feng.servicebase.exception;

import com.feng.servicebase.exception.enums.ChapterExceptionEnum;
import com.feng.servicebase.exception.enums.CourseExceptionEnum;

/**
 * @Author shf
 * @Date 2020/7/17 16:56
 */
public class ChapterException extends BaseException{
    public ChapterException(ChapterExceptionEnum exceptionEnum){
        super(exceptionEnum.getMsg());
        setCode(exceptionEnum.getCode());
        setMsg(exceptionEnum.getMsg());
        setType("【章节信息异常】");
    }
}
