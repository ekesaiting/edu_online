package com.feng.msmservice.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author shf
 * @Date 2020/7/22 15:19
 */
@ConfigurationProperties(prefix = "aliyun.msm")
@Data
public class AliyunMsmProperties {
    private String keyId;
    private String keySecret;
    private String signName;
    private String templateCode;
}
