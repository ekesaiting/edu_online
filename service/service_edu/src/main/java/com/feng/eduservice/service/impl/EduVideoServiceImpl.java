package com.feng.eduservice.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.feng.commonutils.Resp;
import com.feng.commonutils.ResultCodeEnum;
import com.feng.eduservice.client.VodClient;
import com.feng.eduservice.entity.EduVideo;
import com.feng.eduservice.mapper.EduVideoMapper;
import com.feng.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feng.servicebase.exception.BasicException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author shf
 * @since 2020-07-15
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;

    @Override
    @Transactional
    public int deleteVideo(String videoId) {
        EduVideo eduVideo = baseMapper.selectById(videoId);
        String videoSourceId = eduVideo.getVideoSourceId();
        //删除属于该小节的视频
        if (StrUtil.isNotBlank(videoSourceId)){
            Resp resp = vodClient.deleteById(videoSourceId);
            if (!resp.getSuccess()){
                throw new BasicException(ResultCodeEnum.SERVICE_INVOKE_ERROR);
            }
        }
        return baseMapper.deleteById(videoId);
    }

    @Override
    @Transactional
    public int deleteVideoByCourseId(String courseId) {
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id",courseId);
        videoQueryWrapper.select("video_source_id");
        List<EduVideo> eduVideos = baseMapper.selectList(videoQueryWrapper);
        List<String> videoIdList =new ArrayList<>();
        for (EduVideo eduVideo : eduVideos) {
            if(eduVideo!=null){
                String videoSourceId = eduVideo.getVideoSourceId();
                if (StrUtil.isNotBlank(videoSourceId)) {
                    videoIdList.add(videoSourceId);
                }
            }
        }
        //删除阿里云中的视频
        if (videoIdList.size()>0){
            Resp resp = vodClient.deleteByIdBatch(videoIdList);
            if (!resp.getSuccess()){
                throw new BasicException(ResultCodeEnum.SERVICE_INVOKE_ERROR);
            }
        }
        //删除数据库中的视频信息
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        return baseMapper.delete(wrapper);
    }
}
