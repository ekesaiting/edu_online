package com.feng.servicebase.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author shf
 * @Date 2020/7/30 16:57
 */
@Getter
@AllArgsConstructor
public enum UserExceptionEnum {
    USER_NOT_EXIST(28001,"当前用户不存在");

    private final int code;
    private final String msg;
}
