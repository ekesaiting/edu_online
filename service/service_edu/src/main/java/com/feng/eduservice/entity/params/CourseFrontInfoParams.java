package com.feng.eduservice.entity.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author shf
 * @Date 2020/7/25 13:24
 */
@Data
public class CourseFrontInfoParams {
    @ApiModelProperty(value = "课程名称")
    private String title;

    @ApiModelProperty(value = "讲师id")
    private String teacherId;

    @ApiModelProperty(value = "一级类别id")
    private String subjectParentId;

    @ApiModelProperty(value = "二级类别id")
    private String subjectId;

    @ApiModelProperty(value = "销量排序")
    private Boolean buyCountSort;

    @ApiModelProperty(value = "最新时间排序")
    private Boolean gmtCreateSort;

    @ApiModelProperty(value = "价格排序")
    private Boolean priceSort;
}
