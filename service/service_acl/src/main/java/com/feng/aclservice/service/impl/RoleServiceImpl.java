package com.feng.aclservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.feng.aclservice.entity.Role;
import com.feng.aclservice.entity.UserRole;
import com.feng.aclservice.mapper.RoleMapper;
import com.feng.aclservice.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feng.aclservice.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author shf
 * @since 2020-07-30
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public List<Role> selectRoleByUserId(String userId) {
        QueryWrapper<UserRole> userRoleQueryWrapper = new QueryWrapper<>();
        userRoleQueryWrapper.eq("user_id",userId);
        List<UserRole> userRoleList = userRoleService.list(userRoleQueryWrapper);
        List<String> roleIdList = userRoleList.stream()
                .map(UserRole::getRoleId).collect(Collectors.toList());
        List<Role> roleList = new ArrayList<>();
        if(roleIdList.size() > 0) {
            roleList = baseMapper.selectBatchIds(roleIdList);
        }
        return roleList;
    }
}
