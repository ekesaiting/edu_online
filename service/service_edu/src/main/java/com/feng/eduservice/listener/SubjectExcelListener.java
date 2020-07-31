package com.feng.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.feng.eduservice.entity.EduSubject;
import com.feng.eduservice.entity.excel.SubjectData;
import com.feng.eduservice.service.EduSubjectService;
import com.feng.servicebase.exception.SubjectException;
import com.feng.servicebase.exception.enums.SubjectExceptionEnum;

/**
 * @Author shf
 * @Date 2020/7/14 15:33
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    private final EduSubjectService subjectService;

    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null) {
            throw new SubjectException(SubjectExceptionEnum.FILE_EMPTY);
        }
        EduSubject oneLevelSubject = exitsOneLevelSubject(subjectData);

        if (oneLevelSubject == null) {
            oneLevelSubject = new EduSubject();
            oneLevelSubject.setParentId("0");
            oneLevelSubject.setTitle(subjectData.getOneLevelSubjectName());
            subjectService.save(oneLevelSubject);

        }
        String pid = oneLevelSubject.getId();
        EduSubject twoLevelSubject = exitsTwoLevelSubject(subjectData,pid);
        if (twoLevelSubject==null){
            twoLevelSubject=new EduSubject();
            twoLevelSubject.setParentId(pid);
            twoLevelSubject.setTitle(subjectData.getTwoLevelSubjectName());
            subjectService.save(twoLevelSubject);
        }

    }

    private EduSubject exitsOneLevelSubject(SubjectData subjectData) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", subjectData.getOneLevelSubjectName());
        wrapper.eq("parent_id", "0");
        return subjectService.getOne(wrapper);
    }

    private EduSubject exitsTwoLevelSubject(SubjectData subjectData, String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", subjectData.getTwoLevelSubjectName());
        wrapper.eq("parent_id", pid);
        return subjectService.getOne(wrapper);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
