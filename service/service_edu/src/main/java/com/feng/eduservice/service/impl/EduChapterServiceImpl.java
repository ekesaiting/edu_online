package com.feng.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.feng.eduservice.entity.EduChapter;
import com.feng.eduservice.entity.EduVideo;
import com.feng.eduservice.entity.vo.ChapterVO;
import com.feng.eduservice.entity.vo.VideoVO;
import com.feng.eduservice.mapper.EduChapterMapper;
import com.feng.eduservice.mapper.EduVideoMapper;
import com.feng.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feng.eduservice.service.EduVideoService;
import com.feng.servicebase.exception.ChapterException;
import com.feng.servicebase.exception.enums.ChapterExceptionEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author shf
 * @since 2020-07-15
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService videoService;
    @Autowired
    private EduVideoMapper videoMapper;
    @Override
    public List<ChapterVO> getChapterByCourseId(String id) {

        QueryWrapper<EduChapter> chapterWrapper = new QueryWrapper<>();
        chapterWrapper.eq("course_id",id);
        List<EduChapter> chapters = baseMapper.selectList(chapterWrapper);
        QueryWrapper<EduVideo> videoWrapper = new QueryWrapper<>();
        videoWrapper.eq("course_id",id);
        List<EduVideo> videos = videoService.list(videoWrapper);

        List<ChapterVO> chapterVOS=new ArrayList<>();
        for (EduChapter chapter : chapters) {
            ChapterVO chapterVO = new ChapterVO();
            BeanUtils.copyProperties(chapter,chapterVO);
            List<VideoVO> videoVOS = new ArrayList<>();
            for (EduVideo video : videos) {
                if (video.getChapterId().equals(chapter.getId())){
                    VideoVO videoVO = new VideoVO();
                    BeanUtils.copyProperties(video,videoVO);
                    videoVOS.add(videoVO);
                }
            }
            chapterVO.setChildren(videoVOS);
            chapterVOS.add(chapterVO);
        }

        return chapterVOS;
    }

    @Override
    public int deleteChapter(String chapterId) {
        QueryWrapper<EduVideo> videoWrapper = new QueryWrapper<>();
        videoWrapper.eq("chapter_id",chapterId);
        int count = videoService.count(videoWrapper);
        if (count>0){
            throw new ChapterException(ChapterExceptionEnum.VIDEO_EXIST);
        }
        return baseMapper.deleteById(chapterId);


    }
}
