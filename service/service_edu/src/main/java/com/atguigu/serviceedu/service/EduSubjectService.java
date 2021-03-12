package com.atguigu.serviceedu.service;

import com.atguigu.serviceedu.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author atguigu
 * @since 2021-03-11
 */
public interface EduSubjectService extends IService<EduSubject> {

    //上传exeal文件
    void saveSubject(MultipartFile file,EduSubjectService subjectService);
}
