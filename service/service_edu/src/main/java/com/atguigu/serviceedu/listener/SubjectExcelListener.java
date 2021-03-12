package com.atguigu.serviceedu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.servicebase.config.GuliException;
import com.atguigu.serviceedu.entity.EduSubject;
import com.atguigu.serviceedu.entity.excel.SubjectDate;
import com.atguigu.serviceedu.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public class SubjectExcelListener extends AnalysisEventListener<SubjectDate> {

    public EduSubjectService eduSubjectService;

    public SubjectExcelListener() {
    }
    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    // 一行一行读取
    @Override
    public void invoke(SubjectDate subjectDate, AnalysisContext analysisContext) {
        if(subjectDate == null){
            throw new GuliException(20001,"文件数据为空");
        }

        // 一行一行读取，每次读取都是两个值，第一个值是一级分类，第二个值是二级分类，要注意是否是重复值，
        String oneSubjectName = subjectDate.getOneSubjectName();
        String twoSubjectName = subjectDate.getTwoSubjectName();
        // 判断表里是否有一级分类
        EduSubject eduSubject = this.existOneSubject(eduSubjectService, oneSubjectName);
        if (eduSubject == null){
            eduSubject = new EduSubject();
            eduSubject.setParentId("0");
            eduSubject.setTitle(oneSubjectName);
            eduSubjectService.save(eduSubject);
        }

        String pid = eduSubject.getId();

        // 判断表里是否有二级分类
        EduSubject eduSubject1 = this.existTwoSubject(eduSubjectService, twoSubjectName, pid);
        if (eduSubject1 == null){
            eduSubject = new EduSubject();
            eduSubject.setParentId(pid);
            eduSubject.setTitle(twoSubjectName);
            eduSubjectService.save(eduSubject);
        }
    }

    // 判断一级分类不能重复，
    public EduSubject existOneSubject(EduSubjectService eduSubjectService , String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject one = eduSubjectService.getOne(wrapper);
        return one;
    }

    // 判断二级分类不能重复，
    public EduSubject existTwoSubject(EduSubjectService eduSubjectService , String name , String pid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject two = eduSubjectService.getOne(wrapper);
        return two;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
