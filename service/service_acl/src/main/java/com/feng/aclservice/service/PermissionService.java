package com.feng.aclservice.service;

import com.alibaba.fastjson.JSONObject;
import com.feng.aclservice.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.feng.aclservice.entity.params.RolePermissionsParams;

import java.util.List;

/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author shf
 * @since 2020-07-30
 */
public interface PermissionService extends IService<Permission> {

    List<Permission> getAllPermission();

    void removeChildrenById(String permissionId);

    void grantPermissionsToRole(String roleId,List<String> permissionIdList);

    List<String> selectPermissionValueByUserId(String id);

    List<JSONObject> selectPermissionByUserId(String id);

    List<Permission> selectAllMenu(String roleId);

    void saveRolePermissionRealtionShip(String roleId, String[] permissionId);
}
