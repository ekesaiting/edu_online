package com.feng.eduservice.controller;

import com.feng.eduservice.entity.EduTeacher;
import com.feng.eduservice.service.EduTeacherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author shf
 * @Date 2020/7/8 18:25
 */

@SpringBootTest
public class EduTeacherControllerTest{

    @Autowired
    private EduTeacherService teacherService;
    @Test
    public void testUpdate() {
        EduTeacher teacher = teacherService.getById("string");
        teacher.setName("sbbbb");
        teacherService.updateById(teacher);
    }
}