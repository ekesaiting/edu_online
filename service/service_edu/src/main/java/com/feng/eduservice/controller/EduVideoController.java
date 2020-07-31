package com.feng.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.feng.commonutils.Resp;
import com.feng.eduservice.entity.EduVideo;
import com.feng.eduservice.entity.params.VideoParams;
import com.feng.eduservice.entity.vo.VideoVO;
import com.feng.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author shf
 * @since 2020-07-15
 */
@RestController
@RequestMapping("/eduService/video")
public class EduVideoController {
    @Autowired
    private EduVideoService videoService;
    @PostMapping("/addVideo")
    public Resp addVideo(@RequestBody VideoParams videoParam){
        EduVideo eduVideo = new EduVideo();
        BeanUtils.copyProperties(videoParam,eduVideo);
        boolean res = videoService.save(eduVideo);
        return res?Resp.ok():Resp.error();
    }
    @PutMapping("/updateVideo")
    public Resp updateVideo(@RequestBody VideoParams videoParam){
        System.out.println(videoParam);
        EduVideo eduVideo = new EduVideo();
        BeanUtils.copyProperties(videoParam,eduVideo);
        System.out.println(eduVideo);
        boolean res = videoService.updateById(eduVideo);
        return res?Resp.ok():Resp.error();
    }
    @GetMapping("/{videoId}")
    public Resp getVideoByVideoId(@PathVariable("videoId") String id){
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        EduVideo res = videoService.getOne(wrapper);
        VideoVO videoVO = new VideoVO();
        BeanUtils.copyProperties(res,videoVO);
        return Resp.ok().data("videoInfo",videoVO);
    }

    @DeleteMapping("/deleteVideo/{videoId}")
    public Resp deleteVideo(@PathVariable("videoId") String videoId){
        int res=videoService.deleteVideo(videoId);
        return res>0?Resp.ok():Resp.error();
    }
    @DeleteMapping("/deleteVideoByCourseId/{courseId}")
    public Resp deleteVideoByCourseId(@PathVariable("courseId") String courseId){
        int res=videoService.deleteVideoByCourseId(courseId);
        return res>0?Resp.ok():Resp.error();
    }

}

