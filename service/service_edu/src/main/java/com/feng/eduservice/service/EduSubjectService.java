package com.feng.eduservice.service;

import com.feng.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.feng.eduservice.entity.vo.OneLevelSubjectVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author shf
 * @since 2020-07-14
 */
public interface EduSubjectService extends IService<EduSubject> {

    void addSubject(MultipartFile file);

    List<OneLevelSubjectVO> getAllSubject();
}
