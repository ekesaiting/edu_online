package com.feng.msmservice.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.feng.commonutils.ResultCodeEnum;
import com.feng.msmservice.service.MsmService;
import com.feng.msmservice.utils.AliyunMsmProperties;
import com.feng.servicebase.exception.BasicException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @Author shf
 * @Date 2020/7/22 15:06
 */
@Service
public class MsmServiceImpl implements MsmService {
    @Autowired
    private AliyunMsmProperties msmProperties;

    @Override
    public boolean sendMsm(HashMap<String, Object> params, String phoneNumber) {
        if (StrUtil.isBlank(phoneNumber)){
            return false;
        }
        DefaultProfile defaultProfile=DefaultProfile.getProfile("default",
                msmProperties.getKeyId(),
                msmProperties.getKeySecret());
        IAcsClient client = new DefaultAcsClient(defaultProfile);

        CommonRequest request = new CommonRequest();
        //基础参数
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");

        //自定义参数
        request.putQueryParameter("PhoneNumbers",phoneNumber);
        request.putQueryParameter("SignName", msmProperties.getSignName());
        request.putQueryParameter("TemplateCode", msmProperties.getTemplateCode());
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(params));
        try {
            CommonResponse response = client.getCommonResponse(request);
            return response.getHttpResponse().isSuccess();
        } catch (ClientException e) {
            e.printStackTrace();
           return false;
        }
    }
}
