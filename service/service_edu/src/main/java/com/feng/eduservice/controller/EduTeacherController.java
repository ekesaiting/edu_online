package com.feng.eduservice.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feng.commonutils.Resp;
import com.feng.eduservice.entity.EduTeacher;
import com.feng.eduservice.entity.params.TeacherPageParams;
import com.feng.eduservice.entity.params.TeacherParams;
import com.feng.eduservice.service.EduTeacherService;
import com.feng.servicebase.exception.TeacherException;
import com.feng.servicebase.exception.enums.TeacherExceptionEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author shf
 * @since 2020-07-08
 */
@Api(tags = "讲师管理")
@RestController
@RequestMapping("/eduService/teacher")
public class EduTeacherController {
    @Autowired
    public EduTeacherService teacherService;

    @ApiOperation(value = "获取所有讲师列表")
    @GetMapping("/list")
    public Resp findAll() {
        List<EduTeacher> list = teacherService.list();
        return Resp.ok().data("items", list);
    }
    @GetMapping("/{id}")
    public Resp getTeacherById(@PathVariable("id") String id){
        EduTeacher teacher = teacherService.getById(id);
        return Resp.ok().data("teacher",teacher);
    }

    @ApiOperation(value = "根据Id删除讲师")
    @DeleteMapping("/delete/{id}")
    public Resp deleteById(@PathVariable("id") String id) {
        boolean res = teacherService.removeById(id);
        return res ? Resp.ok() : Resp.error().message("讲师不存在");
    }

    @ApiOperation(value = "根据分页查询讲师")
    @GetMapping("/pageList/{page}/{size}")
    public Resp getPageTeacher(@PathVariable("page") int page, @PathVariable("size") int size) {
        Page<EduTeacher> teacherPage = new Page<>(page, size);
        teacherService.page(teacherPage);
        HashMap<String, Object> map = new HashMap<>();
        map.put("items", teacherPage.getRecords());
        map.put("total", teacherPage.getTotal());
        return Resp.ok().data(map);
    }

    @ApiOperation(value = "根据分页和条件查询讲师")
    @PostMapping("/pageConditionList/{page}/{size}")
    public Resp queryList(@PathVariable("page") int page,
                          @PathVariable("size") int size,
                          @RequestBody(required = false) TeacherPageParams query) {
        Page<EduTeacher> teacherPage = new Page<>(page, size);

        String name = query.getName();
        Integer level = query.getLevel();
        String begin = query.getBegin();
        String end = query.getEnd();
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(name))
            wrapper.like("name", name);
        if (level != null)
            wrapper.eq("level", level);
        if (StrUtil.isNotBlank(begin))
            wrapper.ge("gmt_create", begin);
        if (StrUtil.isNotBlank(end))
            wrapper.le("gmt_updated", begin);
        wrapper.orderByDesc("gmt_create");
        Page<EduTeacher> res = teacherService.page(teacherPage, wrapper);
        HashMap<String, Object> map = new HashMap<>();
        map.put("items", teacherPage.getRecords());
        map.put("total", teacherPage.getTotal());
        return Resp.ok().data(map);
    }

    @PostMapping("/save")
    public Resp saveTeacher(@RequestBody TeacherParams query) {
        EduTeacher eduTeacher = new EduTeacher();
        BeanUtils.copyProperties(query, eduTeacher);
        boolean save = teacherService.save(eduTeacher);
        return save ? Resp.ok() : Resp.error();
    }

    @PutMapping("/update")
    public Resp updateTeacher(@RequestBody TeacherParams query) {
        EduTeacher teacher = teacherService.getById(query.getId());
        if (teacher!=null){
            BeanUtils.copyProperties(query,teacher);
            boolean res = teacherService.updateById(teacher);
            return res ? Resp.ok() : Resp.error();
        }
        else{
            throw new TeacherException(TeacherExceptionEnum.TEACHER_NOT_EXIST);
        }

    }

}

