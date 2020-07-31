package com.feng.eduservice.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @Author shf
 * @Date 2020/7/14 14:51
 */
public class Excellistener extends AnalysisEventListener<Student> {

    @Override
    public void invoke(Student student, AnalysisContext analysisContext) {
        System.out.println("***"+student);
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        super.invokeHeadMap(headMap, context);
        System.out.println("表头"+headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
