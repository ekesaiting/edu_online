package com.feng.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feng.commonutils.Resp;
import com.feng.eduservice.entity.EduCourse;
import com.feng.eduservice.entity.EduTeacher;
import com.feng.eduservice.service.EduCourseService;
import com.feng.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @Author shf
 * @Date 2020/7/24 18:01
 */
@RestController
@RequestMapping("eduService/teacherFront")
public class TeacherFrontController {
    @Autowired
    private EduTeacherService teacherService;
    @Autowired
    private EduCourseService courseService;

    @GetMapping("/getTeacherPage/{page}/{size}")
    public Resp getTeacherPage(@PathVariable("page")long page,@PathVariable("size") long size ){
        Page<EduTeacher> teacherPage = new Page<>(page,size);
        HashMap<String,Object> teacherMap= teacherService.getTeacherFrontPage(teacherPage);
        return Resp.ok().data(teacherMap);
    }
    @GetMapping("getTeacherFrontInfo/{teacherId}")
    public Resp getTeacherFrontInfo(@PathVariable("teacherId") String teacherId){
        EduTeacher teacher = teacherService.getById(teacherId);
        QueryWrapper<EduCourse> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.eq("teacher_id",teacherId);
        List<EduCourse> courseList = courseService.list(courseQueryWrapper);
        return Resp.ok().data("teacher",teacher).data("courseList",courseList);
    }
}
