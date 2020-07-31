package com.feng.aclservice.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * @Author shf
 * @Date 2020/7/30 16:45
 */
public interface IndexService {
    Map<String, Object> getUserInfo(String username);

    List<JSONObject> getMenu(String username);

}
