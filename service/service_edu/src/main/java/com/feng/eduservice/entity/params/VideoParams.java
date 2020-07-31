package com.feng.eduservice.entity.params;

import lombok.Data;

/**
 * @Author shf
 * @Date 2020/7/17 21:32
 */
@Data
public class VideoParams {
    private String id;
    private String title;
    private Integer sort;
    private String courseId;
    private String chapterId;
    private Boolean isFree;
    private String videoSourceId;
    private String videoOriginalName;

}
