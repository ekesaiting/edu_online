package com.feng.aclservice.entity.params;

import com.feng.aclservice.entity.Permission;
import lombok.Data;

import java.util.List;

/**
 * @Author shf
 * @Date 2020/7/30 15:43
 */
@Data
public class RolePermissionsParams {
    private String roleId;
    private List<String> permissionIdList;
}
