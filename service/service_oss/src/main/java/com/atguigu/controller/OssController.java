package com.atguigu.controller;

import com.atguigu.commonutils.Rr;
import com.atguigu.service.OssService;
import com.atguigu.service.impl.OssServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/serviceoss/fileoss")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    //上传头像的方法
    @PostMapping
    public Rr uploadOssFile(MultipartFile file){

        String url = ossService.uploadFileAvatar(file);

        return Rr.ok().data("url",url);
    }
}
