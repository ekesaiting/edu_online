package com.feng.servicebase.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author shf
 * @Date 2020/7/14 15:27
 */
@Getter
@AllArgsConstructor
public enum SubjectExceptionEnum {
    FILE_ERROR(23000,"上传的Excel文件错误"),
    FILE_EMPTY(23001,"上传的Excel文件不能为空");

    private final int code;
    private final String msg;
}
