package com.feng.ossservice.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.feng.commonutils.ResultCodeEnum;
import com.feng.ossservice.service.OssService;
import com.feng.ossservice.utils.ConstantPropertiesUtils;
import com.feng.servicebase.exception.BasicException;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @Author shf
 * @Date 2020/7/13 21:28
 */
@Service
public class OssServiceImpl implements OssService {

    @Override
    public String uploadAvatarFile(MultipartFile file) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        final String endpoint = ConstantPropertiesUtils.END_POINT;
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        final String accessKeyId = ConstantPropertiesUtils.KEY_ID;
        final String accessKeySecret = ConstantPropertiesUtils.KEY_SECRET;
        final String bucketName = ConstantPropertiesUtils.BUCKET_NAME;


        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传文件流。
        InputStream inputStream;
        try {
            inputStream = file.getInputStream();
            String fileName = file.getOriginalFilename();
            String uuid= UUID.randomUUID().toString().substring(0,6);
            fileName=uuid+fileName;
            String datePath=new DateTime().toString("yyyy/MM/dd");
            fileName=datePath+"/"+fileName;
            ossClient.putObject(bucketName, fileName, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            return "https://" + bucketName + "." + endpoint + "/" + fileName;


        } catch (IOException e) {
            e.printStackTrace();
            throw new BasicException(ResultCodeEnum.FILE_UPLOAD_ERROR);
        }


    }
}
