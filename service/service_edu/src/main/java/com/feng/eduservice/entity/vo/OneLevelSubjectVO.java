package com.feng.eduservice.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author shf
 * @Date 2020/7/15 14:56
 */
@Data
public class OneLevelSubjectVO {
    private String id;
    private String title;
    private List<TwoLevelSubjectVO> children;
}
