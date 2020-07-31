package com.feng.msmservice.controller;

import cn.hutool.core.util.StrUtil;
import com.feng.commonutils.Resp;
import com.feng.msmservice.service.MsmService;
import com.feng.msmservice.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Author shf
 * @Date 2020/7/22 13:35
 */
@RestController
@RequestMapping("/msmService/")
public class MsmController {
    @Autowired
    private MsmService msmService;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @GetMapping("/send/{phoneNumber}")
    public Resp sendMsm(@PathVariable("phoneNumber") String phoneNumber){


        String code = redisTemplate.opsForValue().get(phoneNumber);
        if (StrUtil.isNotBlank(code)){
            return Resp.ok().message("短信已经发送,五分钟内勿重复点击");
        }
        code= RandomUtil.getFourBitRandom();
        HashMap<String, Object> params = new HashMap<>();
        params.put("code",code);
        boolean isSend=msmService.sendMsm(params,phoneNumber);
        if (isSend){
            redisTemplate.opsForValue().set(phoneNumber,code,5, TimeUnit.MINUTES);
            return Resp.ok();
        }
        return Resp.error().message("短信发送失败");
    }
}
