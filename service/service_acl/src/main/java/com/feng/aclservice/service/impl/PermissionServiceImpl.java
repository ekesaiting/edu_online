package com.feng.aclservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.feng.aclservice.entity.Permission;
import com.feng.aclservice.entity.Role;
import com.feng.aclservice.entity.RolePermission;
import com.feng.aclservice.entity.User;
import com.feng.aclservice.entity.params.RolePermissionsParams;
import com.feng.aclservice.helper.MemuHelper;
import com.feng.aclservice.helper.PermissionHelper;
import com.feng.aclservice.mapper.PermissionMapper;
import com.feng.aclservice.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feng.aclservice.service.RolePermissionService;
import com.feng.aclservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author shf
 * @since 2020-07-30
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private UserService userService;
    @Override
    public List<Permission> getAllPermission() {
        QueryWrapper<Permission> orderWrapper = new QueryWrapper<>();
        orderWrapper.orderByDesc("id");
        List<Permission> allPermissionList = baseMapper.selectList(orderWrapper);
        return buildPermissionList(allPermissionList);
    }

    @Override
    public void removeChildrenById(String permissionId) {
        ArrayList<String> idList = new ArrayList<String>();
        this.selectPermissionChildById(permissionId,idList);
        //把当前id封装到list里面
        idList.add(permissionId);
        baseMapper.deleteBatchIds(idList);
    }

    @Override
    public void grantPermissionsToRole(String roleId,List<String> permissionIdList) {
        ArrayList<RolePermission> rolePermissions = new ArrayList<>();
        for (String permissionId : permissionIdList) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            rolePermissions.add(rolePermission);
        }
        rolePermissionService.saveBatch(rolePermissions);
    }

    @Override
    public List<String> selectPermissionValueByUserId(String id) {

        List<String> selectPermissionValueList = null;
        if(this.isSysAdmin(id)) {
            //如果是系统管理员，获取所有权限
            selectPermissionValueList = baseMapper.selectAllPermissionValue();
        } else {
            selectPermissionValueList = baseMapper.selectPermissionValueByUserId(id);
        }
        return selectPermissionValueList;
    }

    @Override
    public List<JSONObject> selectPermissionByUserId(String userId) {
        List<Permission> selectPermissionList = null;
        if(this.isSysAdmin(userId)) {
            //如果是超级管理员，获取所有菜单
            selectPermissionList = baseMapper.selectList(null);
        } else {
            selectPermissionList = baseMapper.selectPermissionByUserId(userId);
        }

        List<Permission> permissionList = PermissionHelper.bulid(selectPermissionList);
        return MemuHelper.bulid(permissionList);
    }

    private boolean isSysAdmin(String userId) {
        User user = userService.getById(userId);
        return null != user && "admin".equals(user.getUsername());
    }

    private void selectPermissionChildById(String permissionId, ArrayList<String> idList) {
        QueryWrapper<Permission> permissionQueryWrapper = new QueryWrapper<>();
        permissionQueryWrapper.eq("pid",permissionId).select("id");
        List<Permission> permissionList = baseMapper.selectList(permissionQueryWrapper);
        for (Permission permission : permissionList) {
            idList.add(permission.getId());
            selectPermissionChildById(permission.getId(),idList);
        }
    }

    private List<Permission> buildPermissionList(List<Permission> allPermissionList) {
        ArrayList<Permission> finalPermissionList = new ArrayList<>();
        for (Permission permission : allPermissionList) {
            if (permission.getPid().equals("0")){
                permission.setLevel(0);
                finalPermissionList.add(selectChildren(permission,allPermissionList));
            }
        }
        return finalPermissionList;
    }

    private Permission selectChildren(Permission parentPermission, List<Permission> allPermissionList) {
        for (Permission subPermission : allPermissionList) {
            if (subPermission.getPid().equals(parentPermission.getId())){
                if (parentPermission.getChildren()==null){
                    parentPermission.setChildren(new ArrayList<>());
                }
                int level = parentPermission.getLevel()+1;
                subPermission.setLevel(level);
                parentPermission.getChildren().add(selectChildren(subPermission,allPermissionList));
            }
        }
        return parentPermission;
    }
}
