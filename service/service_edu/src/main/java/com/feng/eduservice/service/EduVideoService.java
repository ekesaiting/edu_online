package com.feng.eduservice.service;

import com.feng.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author shf
 * @since 2020-07-15
 */
public interface EduVideoService extends IService<EduVideo> {

    int deleteVideo(String videoId);

    int deleteVideoByCourseId(String courseId);
}
