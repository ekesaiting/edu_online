package com.feng.eduservice.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feng.eduservice.client.VodClient;
import com.feng.eduservice.entity.EduChapter;
import com.feng.eduservice.entity.EduCourse;
import com.feng.eduservice.entity.EduCourseDescription;
import com.feng.eduservice.entity.EduVideo;
import com.feng.eduservice.entity.params.CourseFrontInfoParams;
import com.feng.eduservice.entity.vo.CourseFrontInfoVO;
import com.feng.eduservice.entity.vo.CoursePublishVO;
import com.feng.eduservice.mapper.EduCourseMapper;
import com.feng.eduservice.entity.params.CourseInfoParams;
import com.feng.eduservice.service.EduChapterService;
import com.feng.eduservice.service.EduCourseDescriptionService;
import com.feng.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feng.eduservice.service.EduVideoService;
import com.feng.servicebase.exception.CourseException;
import com.feng.servicebase.exception.enums.CourseExceptionEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author shf
 * @since 2020-07-15
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService descriptionService;
    @Autowired
    private EduVideoService videoService;
    @Autowired
    private EduChapterService chapterService;

    @Override
    @Transactional
    public String saveCourseInfo(CourseInfoParams params) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(params, eduCourse);
        int res = baseMapper.insert(eduCourse);
        if (res <= 0) {
            throw new CourseException(CourseExceptionEnum.SAVE_ERROR);
        }
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(params.getDescription());
        eduCourseDescription.setId(eduCourse.getId());
        descriptionService.save(eduCourseDescription);
        return eduCourse.getId();
    }

    @Override
    public CourseInfoParams getCourseInfoById(String id) {
        EduCourse eduCourse = baseMapper.selectById(id);
        CourseInfoParams courseInfoParams = new CourseInfoParams();
        BeanUtils.copyProperties(eduCourse, courseInfoParams);
        EduCourseDescription description = descriptionService.getById(id);
        courseInfoParams.setDescription(description.getDescription());
        return courseInfoParams;
    }

    @Override
    public String updateCourseInfo(CourseInfoParams params) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(params, eduCourse);
        int res = baseMapper.updateById(eduCourse);
        if (res <= 0) {
            throw new CourseException(CourseExceptionEnum.COURSE_NOT_EXIST);
        }
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(params.getDescription());
        eduCourseDescription.setId(eduCourse.getId());
        descriptionService.updateById(eduCourseDescription);
        return eduCourse.getId();
    }

    @Override
    public CoursePublishVO getCoursePublishInfo(String courseId) {
        return baseMapper.getCoursePublishInfo(courseId);
    }

    @Override
    @Transactional
    public int removeCourse(String courseId) {

        //删除小节
        videoService.deleteVideoByCourseId(courseId);
        //删除章节
        QueryWrapper<EduChapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id",courseId);
         chapterService.remove(chapterQueryWrapper);
        //删除描述
        descriptionService.removeById(courseId);
        //删除课程
        return baseMapper.deleteById(courseId);
    }

    @Override
    @Cacheable(value = "course",key = "'getIndexCourseInfo'")
    public List<EduCourse> getIndexCourseInfo() {
        QueryWrapper<EduCourse> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.orderByDesc("view_count");
        courseQueryWrapper.last("limit 8");
        return baseMapper.selectList(courseQueryWrapper);
    }

    @Override
    public Map<String, Object> getCourseFrontList(Page<EduCourse> eduCoursePage, CourseFrontInfoParams params) {
        QueryWrapper<EduCourse> courseQueryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(params.getSubjectParentId())){
            courseQueryWrapper.eq("subject_parent_id",params.getSubjectParentId());
        }
        if (StrUtil.isNotBlank(params.getSubjectId())){
            courseQueryWrapper.eq("subject_id",params.getSubjectId());
        }
        if (params.getBuyCountSort()!=null&&params.getBuyCountSort()){
            courseQueryWrapper.orderByDesc("buy_count");
        }
        if (params.getGmtCreateSort()!=null&&params.getGmtCreateSort()){
            courseQueryWrapper.orderByDesc("gmt_create");
        }
        if (params.getPriceSort()!=null&&params.getPriceSort()){
            courseQueryWrapper.orderByDesc("price");
        }
        baseMapper.selectPage(eduCoursePage, courseQueryWrapper);

        //手动分页
        List<EduCourse> records = eduCoursePage.getRecords();
        long current = eduCoursePage.getCurrent();
        long pages = eduCoursePage.getPages();
        long size = eduCoursePage.getSize();
        long total = eduCoursePage.getTotal();
        boolean hasNext = eduCoursePage.hasNext();
        boolean hasPrevious = eduCoursePage.hasPrevious();

        Map<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;
    }

    @Override
    public CourseFrontInfoVO getCourseFrontInfo(String courseId) {
        return baseMapper.getCourseFrontInfo(courseId);
    }
}
