package com.feng.eduservice.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;

import java.util.ArrayList;

/**
 * @Author shf
 * @Date 2020/7/14 14:42
 */
public class Test {
    public static void main(String[] args) {
        String filePath="C:\\Users\\shf\\Desktop\\projects\\student.xlsx";
        ArrayList<Student> students = new ArrayList<>();
        Student s1 = new Student();
        s1.setId(123);
        s1.setName("shf");
        Student s2 = new Student();
        s2.setId(456);
        s2.setName("sb");
        students.add(s1);
        students.add(s2);
        EasyExcel.write(filePath, Student.class).sheet("学生列表").doWrite(students);
    }
}
