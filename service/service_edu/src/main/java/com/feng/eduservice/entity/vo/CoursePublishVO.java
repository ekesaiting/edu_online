package com.feng.eduservice.entity.vo;

import lombok.Data;

/**
 * @Author shf
 * @Date 2020/7/18 14:56
 */
@Data
public class CoursePublishVO {
    private String title;
    private String cover;
    private String description;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示


}
