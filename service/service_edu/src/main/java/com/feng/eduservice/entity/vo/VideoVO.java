package com.feng.eduservice.entity.vo;

import lombok.Data;

/**
 * @Author shf
 * @Date 2020/7/17 12:20
 */
@Data
public class VideoVO {
    private String id;
    private String title;
    private Integer sort;
    private Boolean isFree;
    private String videoSourceId;
}
