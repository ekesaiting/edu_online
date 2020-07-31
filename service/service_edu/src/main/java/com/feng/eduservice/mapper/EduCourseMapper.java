package com.feng.eduservice.mapper;

import com.feng.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feng.eduservice.entity.vo.CourseFrontInfoVO;
import com.feng.eduservice.entity.vo.CoursePublishVO;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author shf
 * @since 2020-07-15
 */
@Repository
public interface EduCourseMapper extends BaseMapper<EduCourse> {
     CoursePublishVO getCoursePublishInfo(String courseId);
     CourseFrontInfoVO getCourseFrontInfo(String courseId);


}
