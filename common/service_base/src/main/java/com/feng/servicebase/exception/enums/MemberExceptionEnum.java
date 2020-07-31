package com.feng.servicebase.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author shf
 * @Date 2020/7/22 22:11
 */
@Getter
@AllArgsConstructor
public enum MemberExceptionEnum {
    MEMBER_NOT_EXIST(26000,"用户不存在"),
    MEMBER_IS_DISABLED(26002,"用户被禁用"),
    PASSWORD_ERROR(26001,"密码错误"),
    MEMBER_EXIST(26003,"用户已存在"),
    CODE_ERROR(26004,"验证码错误"),
    CODE_INVALIDATION(26005,"验证码失效"),
    NOT_LOGIN(26006,"用户未登入");

    private final int code;
    private final String msg;
}
