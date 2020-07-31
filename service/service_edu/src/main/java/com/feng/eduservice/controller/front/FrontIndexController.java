package com.feng.eduservice.controller.front;

import com.feng.commonutils.Resp;
import com.feng.eduservice.entity.EduCourse;
import com.feng.eduservice.entity.EduTeacher;
import com.feng.eduservice.service.EduCourseService;
import com.feng.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author shf
 * @Date 2020/7/21 16:36
 */
@RestController
@RequestMapping("eduService/FrontIndex")
public class FrontIndexController {

    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduTeacherService teacherService;

    //查询八个热门课程和四个名师
    @GetMapping("getIndexInfo")
    public Resp getIndexInfo(){
        List<EduCourse> courseList=courseService.getIndexCourseInfo();
        List<EduTeacher> teacherList=teacherService.getIndexTeacherInfo();
        return Resp.ok().data("courseList",courseList).data("teacherList",teacherList);
    }
}
