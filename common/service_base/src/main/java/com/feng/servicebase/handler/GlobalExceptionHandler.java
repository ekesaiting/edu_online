package com.feng.servicebase.handler;

import com.feng.commonutils.ExceptionUtil;
import com.feng.commonutils.Resp;
import com.feng.servicebase.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author shf
 * @Date 2020/7/8 20:08
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //参数未通过jsr303效验规则
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Resp methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        ObjectError error = e.getBindingResult().getAllErrors().get(0);
        log.error("【参数效验异常】{}",error.getDefaultMessage());
        return Resp.error().message(error.getDefaultMessage()).code(30001);
    }
    //自定义异常返回前端
    @ExceptionHandler(BaseException.class)
    public Resp BaseExceptionHandler(BaseException e){
//        log.error(ExceptionUtil.getMessage(e));
        log.error("{} {}",e.getType(),e.getMsg());
        return Resp.error().message(e.getMsg()).code(e.getCode());
    }
    //前端传入的数据格式有误，不能正常解析，例如需要int类型，而前端传入的Json中没有对应类型
    @ExceptionHandler(NumberFormatException.class)
    public Resp numberFormatExceptionHandler(NumberFormatException e){
        log.error("【数据格式异常】{}",e.getMessage());
        return Resp.error().message(e.getMessage()).code(30002);
    }
    //    拦截所有未显式抛出的异常
    @ExceptionHandler(Exception.class)
    public Resp globalExceptionHandle(Exception e){
//        log.error(ExceptionUtil.getMessage(e));
        log.error("【未知异常】{}",e.getMessage());
        return Resp.error().message(e.getMessage()).code(30000);
    }
}
