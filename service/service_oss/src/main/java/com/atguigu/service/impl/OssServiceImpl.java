package com.atguigu.service.impl;

import com.aliyun.oss.OSSClient;
import com.atguigu.service.OssService;
import com.atguigu.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {

    //上传头像到阿里云
    @Override
    public String uploadFileAvatar(MultipartFile file) {

        //获取阿里云存储相关常量
        String endPoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        try {
            //判断oss实例是否存在：如果不存在则创建，如果存在则获取
            OSSClient ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);
            //获取上传文件流
            InputStream inputStream = file.getInputStream();
            //获取文件名称
            String originalFilename = file.getOriginalFilename();

            //uuid
            String uuid = UUID.randomUUID().toString().replace("-", "");

            //将uuid拼接到名字上
            originalFilename = uuid + originalFilename;

            String datePath = new DateTime().toString("yyyy/MM/dd");

            //将文件路径拼接
            originalFilename = datePath +"/"+ originalFilename;

            ossClient.putObject(bucketName,originalFilename,inputStream);

            //关闭
            ossClient.shutdown();

            // https://newedu2341.oss-cn-beijing.aliyuncs.com/640.gif
            String url = "https://"+bucketName+"."+endPoint+"/"+originalFilename;
            return url;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
