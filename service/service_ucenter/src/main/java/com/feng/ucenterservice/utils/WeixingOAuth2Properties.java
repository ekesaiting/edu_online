package com.feng.ucenterservice.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author shf
 * @Date 2020/7/24 13:57
 */
@ConfigurationProperties(prefix = "weixing.open")
@Data
public class WeixingOAuth2Properties {
    private String appId;
    private String appSecret;
    private String redirectUrl;
}
