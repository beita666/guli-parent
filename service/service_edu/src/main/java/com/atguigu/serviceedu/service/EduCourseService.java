package com.atguigu.serviceedu.service;

import com.atguigu.serviceedu.entity.EduCourse;
import com.atguigu.serviceedu.entity.vo.CourseInfoForm;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author atguigu
 * @since 2021-03-16
 */
public interface EduCourseService extends IService<EduCourse> {

    //添加课程
    void addCourseInfo(CourseInfoForm courseInfoForm);
}
