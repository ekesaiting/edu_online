package com.feng.aclservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author shf
 * @Date 2020/7/30 13:53
 */
@SpringBootApplication
@ComponentScan("com.feng")
@MapperScan("com.feng.aclservice.mapper")
@EnableDiscoveryClient
public class AclApplication {
    public static void main(String[] args) {
        SpringApplication.run(AclApplication.class,args);
    }
}
