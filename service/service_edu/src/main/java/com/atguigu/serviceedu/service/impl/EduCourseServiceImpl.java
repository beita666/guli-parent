package com.atguigu.serviceedu.service.impl;

import com.atguigu.servicebase.config.GuliException;
import com.atguigu.serviceedu.entity.EduCourse;
import com.atguigu.serviceedu.entity.EduCourseDescription;
import com.atguigu.serviceedu.entity.vo.CourseInfoForm;
import com.atguigu.serviceedu.mapper.EduCourseMapper;
import com.atguigu.serviceedu.service.EduCourseDescriptionService;
import com.atguigu.serviceedu.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2021-03-16
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {


    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;



    @Override
    public void addCourseInfo(CourseInfoForm courseInfoForm) {

        // 课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert==0){
            throw new GuliException(20001,"添加课程信息失败");
        }


        //描述表
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        //将课程表id和描述表id设置为同一个，这样两个表之间就会有联系
        eduCourseDescription.setId(eduCourse.getId());
        eduCourseDescription.setDescription(courseInfoForm.getDescription());
        eduCourseDescriptionService.save(eduCourseDescription);
    }
}
