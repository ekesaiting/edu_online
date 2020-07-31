package com.feng.servicebase.exception;

import com.feng.commonutils.ResultCodeEnum;

/**
 * @Author shf
 * @Date 2020/7/20 12:21
 */
public class BasicException  extends BaseException{
    public BasicException(ResultCodeEnum exceptionEnum){
        super(exceptionEnum.getMessage());
        setCode(exceptionEnum.getCode());
        setMsg(exceptionEnum.getMessage());
        setType("【基础服务异常】");
    }
}
