package com.feng.vodservice.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
/**
 * @Author shf
 * @Date 2020/7/20 11:25
 */
@ConfigurationProperties(prefix = "aliyun.vod.file")
@Data
public class AliyunVodPropertiesUtil {
    private String keyId;
    private String keySecret;
}
