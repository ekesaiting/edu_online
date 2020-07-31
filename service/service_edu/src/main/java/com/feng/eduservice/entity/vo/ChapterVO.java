package com.feng.eduservice.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author shf
 * @Date 2020/7/17 12:19
 */
@Data
public class ChapterVO {
    private String id;
    private String title;
    private int sort;
    private List<VideoVO> children;
}
