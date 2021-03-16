package com.atguigu.serviceedu.controller;


import com.atguigu.commonutils.Rr;
import com.atguigu.serviceedu.entity.vo.CourseInfoForm;
import com.atguigu.serviceedu.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2021-03-16
 */
@RestController
@RequestMapping("/serviceedu/edu-course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;


    @PostMapping("addCourseInfo")
    public Rr addCourseInfo(@RequestBody CourseInfoForm courseInfoForm){

        eduCourseService.addCourseInfo(courseInfoForm);

        return Rr.ok();
    }
}

