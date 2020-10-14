package com.feng.aclservice.controller;


import com.feng.aclservice.entity.Permission;
import com.feng.aclservice.entity.params.RolePermissionsParams;
import com.feng.aclservice.service.PermissionService;
import com.feng.commonutils.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 权限 前端控制器
 * </p>
 *
 * @author shf
 * @since 2020-07-30
 */
@RestController
@RequestMapping("/aclService/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;
    @GetMapping("/list")
    public Resp list(){
        List<Permission> permissionList=permissionService.getAllPermission();
        return Resp.ok().data("permissionList",permissionList);
    }
    @DeleteMapping("/remove/{permissionId}")
    public Resp deletePermission(@PathVariable("permissionId") String permissionId){
        permissionService.removeChildrenById(permissionId);
        return Resp.ok();
    }
    @PostMapping("/doAssign")
    public Resp doAssign(String roleId,String[] permissionId) {
        permissionService.saveRolePermissionRealtionShip(roleId,permissionId);
        return Resp.ok();
    }
    @GetMapping("toAssign/{roleId}")
    public Resp toAssign(@PathVariable String roleId) {
        List<Permission> list = permissionService.selectAllMenu(roleId);
        return Resp.ok().data("children", list);
    }
    @PostMapping("save")
    public Resp save(@RequestBody Permission permission) {
        permissionService.save(permission);
        return Resp.ok();
    }

    @PutMapping("update")
    public Resp updateById(@RequestBody Permission permission) {
        permissionService.updateById(permission);
        return Resp.ok();
    }

}

