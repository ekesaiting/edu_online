package com.feng.eduservice.controller.front;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feng.commonutils.JwtUtils;
import com.feng.commonutils.Resp;
import com.feng.commonutils.ordervo.CourseFrontInfoVOOrder;
import com.feng.eduservice.client.OrderClient;
import com.feng.eduservice.entity.EduCourse;
import com.feng.eduservice.entity.params.CourseFrontInfoParams;
import com.feng.eduservice.entity.vo.ChapterVO;
import com.feng.eduservice.entity.vo.CourseFrontInfoVO;
import com.feng.eduservice.service.EduChapterService;
import com.feng.eduservice.service.EduCourseService;
import com.feng.servicebase.exception.MemberException;
import com.feng.servicebase.exception.enums.MemberExceptionEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Author shf
 * @Date 2020/7/25 13:26
 */
@RestController
@RequestMapping("/eduService/courseFront")
public class CourseFrontController {
    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduChapterService chapterService;
    @Autowired
    private OrderClient orderClient;

    @PostMapping("/pageConditionList/{page}/{size}")
    public Resp pageConditionList(@PathVariable("page") long page,
                                  @PathVariable("size") long size,
                                  @RequestBody(required = false) CourseFrontInfoParams params) {
        Page<EduCourse> eduCoursePage = new Page<>(page, size);
        Map<String, Object> map = courseService.getCourseFrontList(eduCoursePage, params);
        return Resp.ok().data(map);
    }

    @GetMapping("/getCourseInfo/{courseId}")
    public Resp getCourseInfo(HttpServletRequest request,@PathVariable("courseId") String courseId) {
        CourseFrontInfoVO courseFrontInfoVO = courseService.getCourseFrontInfo(courseId);
        List<ChapterVO> chapterVOList = chapterService.getChapterByCourseId(courseId);
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //TODO 解决controller方法会执行两次的问题
        Boolean isBuy = false;
//        免费就不查询是否购买
        if (courseFrontInfoVO.getPrice().doubleValue() != 0) {
            isBuy = orderClient.getCoursePayStatus(courseId, memberId);
        }
        return Resp.ok().data("courseInfo", courseFrontInfoVO)
                .data("chapterList", chapterVOList)
                .data("isBuy", isBuy);
    }

    @GetMapping("/getCourseInfoForOrder/{courseId}")
    public CourseFrontInfoVOOrder getCourseInfoForOrder(@PathVariable("courseId") String courseId) {
        CourseFrontInfoVO courseFrontInfoVO = courseService.getCourseFrontInfo(courseId);
        CourseFrontInfoVOOrder eduCourseOrder = new CourseFrontInfoVOOrder();
        BeanUtils.copyProperties(courseFrontInfoVO, eduCourseOrder);
        return eduCourseOrder;
    }
}
