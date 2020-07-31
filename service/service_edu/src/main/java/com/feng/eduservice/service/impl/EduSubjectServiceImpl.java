package com.feng.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feng.eduservice.entity.EduSubject;
import com.feng.eduservice.entity.excel.SubjectData;
import com.feng.eduservice.entity.vo.OneLevelSubjectVO;
import com.feng.eduservice.entity.vo.TwoLevelSubjectVO;
import com.feng.eduservice.listener.SubjectExcelListener;
import com.feng.eduservice.mapper.EduSubjectMapper;
import com.feng.eduservice.service.EduSubjectService;
import com.feng.servicebase.exception.SubjectException;
import com.feng.servicebase.exception.enums.SubjectExceptionEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author shf
 * @since 2020-07-14
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Autowired
    private EduSubjectMapper subjectMapper;

    @Override
    public void addSubject(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, SubjectData.class,new SubjectExcelListener(this)).sheet().doRead();

        } catch (IOException e) {
           throw new SubjectException(SubjectExceptionEnum.FILE_ERROR);
        }
    }

    @Override
    public List<OneLevelSubjectVO> getAllSubject() {
        QueryWrapper<EduSubject> OneLevelWrapper = new QueryWrapper<>();
        OneLevelWrapper.eq("parent_id","0");
        List<EduSubject> oneLevelSubjects = subjectMapper.selectList(OneLevelWrapper);
        List<OneLevelSubjectVO> oneLevelSubjectVOS=new ArrayList<>();

        QueryWrapper<EduSubject> twoLevelWrapper = new QueryWrapper<>();
        twoLevelWrapper.ne("parent_id","0");
        List<EduSubject> twoLevelSubjects = subjectMapper.selectList(twoLevelWrapper);

        for (EduSubject oneLevelSubject : oneLevelSubjects) {
            OneLevelSubjectVO oneLevelSubjectVO = new OneLevelSubjectVO();
            BeanUtils.copyProperties(oneLevelSubject,oneLevelSubjectVO);
            ArrayList<TwoLevelSubjectVO> twoLevelSubjectVOS = new ArrayList<>();
            for (EduSubject twoLevelSubject : twoLevelSubjects) {
                if (twoLevelSubject.getParentId().equals(oneLevelSubject.getId())){
                    TwoLevelSubjectVO twoLevelSubjectVO = new TwoLevelSubjectVO();
                    BeanUtils.copyProperties(twoLevelSubject,twoLevelSubjectVO);
                    twoLevelSubjectVOS.add(twoLevelSubjectVO);
                }
            }
            oneLevelSubjectVO.setChildren(twoLevelSubjectVOS);
            oneLevelSubjectVOS.add(oneLevelSubjectVO);
        }

        /*for (EduSubject eduSubject : eduSubjects) {
            OneLevelSubjectVO oneLevelSubjectVO = new OneLevelSubjectVO();
            oneLevelSubjectVO.setId(eduSubject.getId());
            oneLevelSubjectVO.setLabel(eduSubject.getTitle());
            QueryWrapper<EduSubject> twoLevelWrapper = new QueryWrapper<>();
            twoLevelWrapper.eq("parent_id",eduSubject.getId());
            List<EduSubject> twoLevelSubjects = subjectMapper.selectList(twoLevelWrapper);
            ArrayList<TwoLevelSubjectVO> twoLevelSubjectVOS = new ArrayList<>();
            for (EduSubject twoLevelSubject : twoLevelSubjects) {
                TwoLevelSubjectVO twoLevelSubjectVO = new TwoLevelSubjectVO();
                twoLevelSubjectVO.setId(twoLevelSubject.getId());
                twoLevelSubjectVO.setLabel(twoLevelSubject.getTitle());
                twoLevelSubjectVOS.add(twoLevelSubjectVO);
            }
            oneLevelSubjectVO.setChildren(twoLevelSubjectVOS);
            oneLevelSubjectVOS.add(oneLevelSubjectVO);
        }*/
        return oneLevelSubjectVOS;
    }
}
