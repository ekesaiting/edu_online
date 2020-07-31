package com.feng.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feng.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.feng.eduservice.entity.params.CourseFrontInfoParams;
import com.feng.eduservice.entity.params.CourseInfoParams;
import com.feng.eduservice.entity.vo.CourseFrontInfoVO;
import com.feng.eduservice.entity.vo.CoursePublishVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author shf
 * @since 2020-07-15
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoParams params);
    CourseInfoParams getCourseInfoById(String id);

    String updateCourseInfo(CourseInfoParams params);

    CoursePublishVO getCoursePublishInfo(String courseId);

    int removeCourse(String courseId);

    List<EduCourse> getIndexCourseInfo();

    Map<String, Object> getCourseFrontList(Page<EduCourse> eduCoursePage, CourseFrontInfoParams params);

    CourseFrontInfoVO getCourseFrontInfo(String courseId);
}
