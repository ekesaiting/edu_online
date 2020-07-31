package com.feng.servicebase.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author shf
 * @Date 2020/7/17 16:54
 */
@Getter
@AllArgsConstructor
public enum ChapterExceptionEnum {
    VIDEO_EXIST(25000,"删除失败，存在未被删除的小节");

    private final int code;
    private final String msg;
}
