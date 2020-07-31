package com.feng.servicebase.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author shf
 * @Date 2020/7/15 18:22
 */
@Getter
@AllArgsConstructor
public enum CourseExceptionEnum {
    SAVE_ERROR(24000,"添加课程失败"),
    COURSE_NOT_EXIST(24001,"课程不存在");

    private final int code;
    private final String msg;
}
