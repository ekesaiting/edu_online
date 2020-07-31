package com.feng.eduservice.excel;

import com.alibaba.excel.EasyExcel;

/**
 * @Author shf
 * @Date 2020/7/14 14:54
 */
public class TestRead {
    public static void main(String[] args) {
        String filePath="C:\\Users\\shf\\Desktop\\projects\\student.xlsx";
        EasyExcel.read(filePath,Student.class,new Excellistener()).sheet().doRead();
    }
}
