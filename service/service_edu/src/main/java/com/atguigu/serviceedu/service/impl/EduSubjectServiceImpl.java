package com.atguigu.serviceedu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.serviceedu.entity.EduSubject;
import com.atguigu.serviceedu.entity.excel.SubjectDate;
import com.atguigu.serviceedu.entity.subject.OneSubject;
import com.atguigu.serviceedu.entity.subject.TwoSubject;
import com.atguigu.serviceedu.listener.SubjectExcelListener;
import com.atguigu.serviceedu.mapper.EduSubjectMapper;
import com.atguigu.serviceedu.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2021-03-11
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {


    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {
        try {
            InputStream in = file.getInputStream();
            EasyExcel.read(in, SubjectDate.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        // 查询一级
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", "0");
        List<EduSubject> eduSubjects = baseMapper.selectList(wrapper);
        // 查询二级
        QueryWrapper<EduSubject> twowrapper = new QueryWrapper<>();
        twowrapper.ne("parent_id", "0");
        List<EduSubject> eduSubjectList = baseMapper.selectList(twowrapper);

        //
        List<OneSubject> oneSubjectList = new ArrayList<>();

        for (EduSubject eduSubject : eduSubjects){
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(eduSubject,oneSubject);


            List<TwoSubject> twoSubjectList = new ArrayList<>();

            for (EduSubject eduSubject1:eduSubjectList) {
                TwoSubject twoSubject = new TwoSubject();
                if (eduSubject.getId().equals(eduSubject1.getParentId())){
                    BeanUtils.copyProperties(eduSubject1,twoSubject);
                    twoSubjectList.add(twoSubject);
                }
            }
            oneSubject.setChildren(twoSubjectList);
            oneSubjectList.add(oneSubject);
        }



        return oneSubjectList;
    }
}
