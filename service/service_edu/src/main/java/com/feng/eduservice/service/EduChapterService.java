package com.feng.eduservice.service;

import com.feng.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.feng.eduservice.entity.vo.ChapterVO;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author shf
 * @since 2020-07-15
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVO> getChapterByCourseId(String id);

    int deleteChapter(String chapterId);
}
