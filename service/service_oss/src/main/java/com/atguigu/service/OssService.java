package com.atguigu.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {

    //上传头像到阿里云
    String uploadFileAvatar(MultipartFile file);
}
