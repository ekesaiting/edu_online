package com.feng.ossservice.utils;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author shf
 * @Date 2020/7/13 21:15
 */
@Component
//@ConfigurationProperties(prefix = "aliyun.oss.file")
@Data
public class ConstantPropertiesUtils implements InitializingBean {
    @Value("${aliyun.oss.file.endPoint}")
    private String endPoint;
    @Value("${aliyun.oss.file.keyId}")
    private String keyId;
    @Value("${aliyun.oss.file.keySecret}")
    private String keySecret;
    @Value("${aliyun.oss.file.bucketName}")
    private String bucketName;
    public static  String END_POINT;
    public static String KEY_ID;
    public static String KEY_SECRET;
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT=endPoint;
        KEY_ID=keyId;
        KEY_SECRET=keySecret;
        BUCKET_NAME=bucketName;
    }
}
