package com.feng.eduservice.controller;


import com.feng.commonutils.Resp;
import com.feng.eduservice.entity.vo.OneLevelSubjectVO;
import com.feng.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author shf
 * @since 2020-07-14
 */
@Api(tags = "讲师分类管理")
@RestController
@RequestMapping("/eduService/subject")
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    @PostMapping("/addSubject")
    public Resp addSubject(MultipartFile file) {
        subjectService.addSubject(file);
        return Resp.ok();
    }
    @GetMapping("/list")
    public Resp list(){
        List<OneLevelSubjectVO> allSubject = subjectService.getAllSubject();
        return Resp.ok().data("items",allSubject);
    }

}

