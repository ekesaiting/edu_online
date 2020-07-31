package com.feng.eduservice.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author shf
 * @Date 2020/7/14 14:40
 */
@Data
public class Student {
    @ExcelProperty(value = "学生姓名",index = 0)
    private String name;
    @ExcelProperty(value = "学生编号",index = 1)
    private Integer id;
}
