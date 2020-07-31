package com.feng.eduservice.controller;


import com.feng.commonutils.Resp;
import com.feng.eduservice.entity.EduChapter;
import com.feng.eduservice.entity.params.ChapterParams;
import com.feng.eduservice.entity.vo.ChapterVO;
import com.feng.eduservice.service.EduChapterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author shf
 * @since 2020-07-15
 */
@RestController
@RequestMapping("/eduService/chapter")
public class EduChapterController {

    @Autowired
    private EduChapterService chapterService;
    @GetMapping("/getChapterByCourseId/{courseId}")
    public Resp getChapterByCourseId(@PathVariable("courseId") String id){
        List<ChapterVO> chapterVOList=chapterService.getChapterByCourseId(id);
        return Resp.ok().data("chapterList",chapterVOList);
    }
    @PostMapping("/addChapter")
    public Resp addChapter(@RequestBody ChapterParams chapterParams){
        EduChapter eduChapter = new EduChapter();
        BeanUtils.copyProperties(chapterParams,eduChapter);
        chapterService.save(eduChapter);
        return Resp.ok();
    }
    @PutMapping("/updateChapter")
    public Resp updateChapter(@RequestBody ChapterParams chapterParams){
        EduChapter eduChapter = new EduChapter();
        BeanUtils.copyProperties(chapterParams,eduChapter);
        chapterService.updateById(eduChapter);
        return Resp.ok();
    }
    @DeleteMapping("/deleteChapter/{chapterId}")
    public Resp deleteChapter(@PathVariable("chapterId") String chapterId){
        int res = chapterService.deleteChapter(chapterId);
        return res>0?Resp.ok():Resp.error();
    }
    @GetMapping("/{chapterId}")
    public Resp getChapterByChapterId(@PathVariable("chapterId") String chapterId){
        EduChapter chapter = chapterService.getById(chapterId);
        ChapterVO chapterVO = new ChapterVO();
        BeanUtils.copyProperties(chapter,chapterVO);
        return Resp.ok().data("chapterInfo",chapterVO);
    }

}

