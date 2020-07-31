package com.feng.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feng.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author shf
 * @since 2020-07-08
 */
public interface EduTeacherService extends IService<EduTeacher> {

    List<EduTeacher> getIndexTeacherInfo();

    HashMap<String, Object> getTeacherFrontPage(Page<EduTeacher> teacherPAge);
}
