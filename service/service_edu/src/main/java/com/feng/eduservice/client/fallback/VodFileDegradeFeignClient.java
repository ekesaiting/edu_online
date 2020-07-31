package com.feng.eduservice.client.fallback;

import com.feng.commonutils.Resp;
import com.feng.eduservice.client.VodClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author shf
 * @Date 2020/7/20 22:04
 */
@Component
public class VodFileDegradeFeignClient  implements VodClient {
    @Override
    public Resp deleteById(String vodId) {
        return Resp.error().message("服务调用出现问题，删除视频失败");
    }

    @Override
    public Resp deleteByIdBatch(List<String> vodIdList) {
        return Resp.error().message("服务调用出现问题，删除多个视频失败");
    }
}
