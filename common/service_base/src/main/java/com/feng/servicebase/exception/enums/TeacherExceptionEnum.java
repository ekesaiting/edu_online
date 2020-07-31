package com.feng.servicebase.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author shf
 * @Date 2020/7/8 20:33
 */
@AllArgsConstructor
@Getter
public enum TeacherExceptionEnum {
    TEACHER_NOT_EXIST(22000,"当前讲师不存在");

    private final int code;
    private final String msg;
}
