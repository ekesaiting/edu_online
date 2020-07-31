package com.feng.msmservice.service;

import java.util.HashMap;

/**
 * @Author shf
 * @Date 2020/7/22 15:05
 */
public interface MsmService {
    boolean sendMsm(HashMap<String, Object> params, String phoneNumber);
}
