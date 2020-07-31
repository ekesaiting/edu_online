package com.feng.aclservice.service;

import com.feng.aclservice.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author shf
 * @since 2020-07-30
 */
public interface UserService extends IService<User> {

    User selectByUsername(String username);
}
