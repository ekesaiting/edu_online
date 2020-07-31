package com.feng.ucenterservice;

import com.feng.ucenterservice.utils.WeixingOAuth2Properties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author shf
 * @Date 2020/7/22 21:56
 */
@SpringBootApplication
@ComponentScan("com.feng")
@MapperScan("com.feng.ucenterservice.mapper")
@EnableConfigurationProperties(WeixingOAuth2Properties.class)
@EnableDiscoveryClient
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class,args);
    }
}
