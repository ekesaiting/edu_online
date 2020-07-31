package com.feng.eduservice.client.fallback;

import com.feng.commonutils.ResultCodeEnum;
import com.feng.eduservice.client.OrderClient;
import com.feng.servicebase.exception.BasicException;
import org.springframework.stereotype.Component;

/**
 * @Author shf
 * @Date 2020/7/27 17:07
 */
@Component
public class OrderDegradeFeignClient implements OrderClient {
    @Override
    public Boolean getCoursePayStatus(String courseId, String memberId) {
        System.out.println("服务调用失败");
        throw new BasicException(ResultCodeEnum.SERVICE_INVOKE_ERROR);
    }
}
