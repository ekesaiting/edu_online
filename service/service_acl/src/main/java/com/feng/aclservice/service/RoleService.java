package com.feng.aclservice.service;

import com.feng.aclservice.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shf
 * @since 2020-07-30
 */
public interface RoleService extends IService<Role> {

    List<Role> selectRoleByUserId(String id);

}
