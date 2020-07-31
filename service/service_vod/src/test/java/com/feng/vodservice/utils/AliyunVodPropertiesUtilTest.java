package com.feng.vodservice.utils;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author shf
 * @Date 2020/7/20 11:35
 */
@SpringBootTest
public class AliyunVodPropertiesUtilTest {

    @Autowired
    private AliyunVodPropertiesUtil propertiesUtil;
    @Test
    public void test(){
        System.out.println(propertiesUtil.getKeyId());
        System.out.println(propertiesUtil.getKeySecret());
    }
}