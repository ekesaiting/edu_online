package com.feng.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author shf
 * @Date 2020/7/14 15:23
 */
@Data
public class SubjectData {
    @ExcelProperty(index = 0)
    private String oneLevelSubjectName;
    @ExcelProperty(index = 1)
    private String twoLevelSubjectName;
}
