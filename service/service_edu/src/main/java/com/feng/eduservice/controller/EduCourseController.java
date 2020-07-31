package com.feng.eduservice.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feng.commonutils.Resp;
import com.feng.eduservice.entity.EduCourse;
import com.feng.eduservice.entity.params.CourseInfoParams;
import com.feng.eduservice.entity.params.CoursePageParams;
import com.feng.eduservice.entity.vo.CoursePublishVO;
import com.feng.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author shf
 * @since 2020-07-15
 */
@RestController
@RequestMapping("/eduService/course")
public class EduCourseController {
    @Autowired
    private EduCourseService courseService;

    @PostMapping("/addCourse")
    public Resp addCourse(@RequestBody CourseInfoParams params){
        String courseId = courseService.saveCourseInfo(params);
        return Resp.ok().data("courseId",courseId);
    }
    @GetMapping("/{id}")
    public Resp getCourseById(@PathVariable("id") String id){
        CourseInfoParams courseInfoParams = courseService.getCourseInfoById(id);
        return Resp.ok().data("courseInfo",courseInfoParams);
    }
    @PutMapping("/update")
    public Resp update(@RequestBody CourseInfoParams params){
        String id= courseService.updateCourseInfo(params);
        return Resp.ok().data("courseId",id);

    }
    @GetMapping("/getCoursePublishInfo/{courseId}")
    public Resp getCoursePublishInfo(@PathVariable("courseId") String courseId){
        CoursePublishVO coursePublishVO= courseService.getCoursePublishInfo(courseId);
        return Resp.ok().data("coursePublishInfo",coursePublishVO);
    }
    @PutMapping("/publishCourse/{courseId}")
    public Resp publishCourse(@PathVariable("courseId") String courseId){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(courseId);
        eduCourse.setStatus("Normal");
        courseService.updateById(eduCourse);
        return Resp.ok();
    }
    @GetMapping("/list")
    public Resp findAll(){
        List<EduCourse> list = courseService.list();
        return Resp.ok().data("courseList",list);
    }
    @GetMapping("/list/{page}/{size}")
    public Resp pageList(@PathVariable("page") int page,@PathVariable("size") int size){
        Page<EduCourse> eduCoursePage = new Page<>(page,size);
        courseService.page(eduCoursePage);
        Map<String ,Object> map=new HashMap<>();
        map.put("total",eduCoursePage.getTotal());
        map.put("courseList",eduCoursePage.getRecords());
        return Resp.ok().data(map);
    }
    @PostMapping("/pageConditionList/{page}/{size}")
    public Resp pageConditionList(@PathVariable("page") int page,
                                  @PathVariable("size") int size,
                                  @RequestBody(required = false) CoursePageParams params){
        Page<EduCourse> eduCoursePage = new Page<>(page,size);
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        String name=params.getName();
        String status=params.getStatus();
        String begin=params.getBegin();
        if (StrUtil.isNotBlank(name))
            wrapper.like("title", name);
        if (StrUtil.isNotBlank(status))
            wrapper.eq("status", status);
        if (StrUtil.isNotBlank(begin))
            wrapper.ge("gmt_create", begin);
        courseService.page(eduCoursePage,wrapper);
        wrapper.orderByDesc("gmt_create");
        Map<String ,Object> map=new HashMap<>();
        map.put("total",eduCoursePage.getTotal());
        map.put("courseList",eduCoursePage.getRecords());
        return Resp.ok().data(map);

    }
    @DeleteMapping("/deleteCourse/{courseId}")
    public Resp deleteCourse(@PathVariable("courseId") String courseId){
        int res=courseService.removeCourse(courseId);
        return  res>0?Resp.ok():Resp.error();
    }

    

}

