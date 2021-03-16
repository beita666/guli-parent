package com.atguigu.serviceedu.controller;


import com.atguigu.commonutils.Rr;
import com.atguigu.serviceedu.entity.subject.OneSubject;
import com.atguigu.serviceedu.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2021-03-11
 */
@RestController
@RequestMapping("/serviceedu/edu-subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;


    // 获取上传文件
    @PostMapping("saveSubject")
    public Rr saveSubject(MultipartFile file){

        eduSubjectService.saveSubject(file,eduSubjectService);

        return Rr.ok();
    }


    @GetMapping("getSubjectAll")
    public Rr getSubjectAll(){
        List<OneSubject> list = eduSubjectService.getAllOneTwoSubject();
        return Rr.ok().data("list",list);
    }
}

