package com.feng.aclservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.feng.aclservice.entity.Role;
import com.feng.aclservice.entity.User;
import com.feng.aclservice.service.IndexService;
import com.feng.aclservice.service.PermissionService;
import com.feng.aclservice.service.RoleService;
import com.feng.aclservice.service.UserService;
import com.feng.servicebase.exception.UserException;
import com.feng.servicebase.exception.enums.UserExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author shf
 * @Date 2020/7/30 16:45
 */
@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Override
    public Map<String, Object> getUserInfo(String username) {
        Map<String, Object> result = new HashMap<>();
        User user = userService.selectByUsername(username);
        if (null == user) {
            throw new UserException(UserExceptionEnum.USER_NOT_EXIST);
        }

        //根据用户id获取角色
        List<Role> roleList = roleService.selectRoleByUserId(user.getId());
        List<String> roleNameList = roleList.stream().map(Role::getRoleName).collect(Collectors.toList());
        if(roleNameList.size() == 0) {
            //前端框架必须返回一个角色，否则报错，如果没有角色，返回一个空角色
            roleNameList.add("");
        }

        //根据用户id获取操作权限值
        List<String> permissionValueList = permissionService.selectPermissionValueByUserId(user.getId());
        redisTemplate.opsForValue().set(username, permissionValueList);

        result.put("name", user.getUsername());
        result.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        result.put("roles", roleNameList);
        result.put("permissionValueList", permissionValueList);
        return result;
    }

    @Override
    public List<JSONObject> getMenu(String username) {
        User user = userService.selectByUsername(username);

        //根据用户id获取用户菜单权限
        return permissionService.selectPermissionByUserId(user.getId());
    }
}
