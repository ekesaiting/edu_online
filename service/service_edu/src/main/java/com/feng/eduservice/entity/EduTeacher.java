package com.feng.eduservice.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 讲师
 * </p>
 *
 * @author shf
 * @since 2020-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="EduTeacher对象", description="讲师")
public class EduTeacher implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "讲师ID" ,example = "")
      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "讲师姓名")
    private String name;

    @ApiModelProperty(value = "讲师简介")
    private String intro;

    @ApiModelProperty(value = "讲师资历,一句话说明讲师")
    private String career;

    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;

    @ApiModelProperty(value = "讲师头像")
    private String avatar;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除",example = "false")
    @TableLogic
    private Boolean isDeleted;

    @ApiModelProperty(value = "创建时间",example = "2020-07-07 10:20:30")
      @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间",example = "2020-07-07 10:20:30")
      @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
