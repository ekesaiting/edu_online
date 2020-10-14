package com.feng.aclservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feng.aclservice.entity.User;
import com.feng.aclservice.service.RoleService;
import com.feng.aclservice.service.UserService;
import com.feng.commonutils.MD5;
import com.feng.commonutils.Resp;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author shf
 * @since 2020-07-30
 */
@RestController
@RequestMapping("/aclService/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "获取管理用户分页列表")
    @GetMapping("/{page}/{limit}")
    public Resp index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
                    User userQueryVo) {
        Page<User> pageParam = new Page<>(page, limit);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(userQueryVo.getUsername())) {
            wrapper.like("username",userQueryVo.getUsername());
        }
        IPage<User> pageModel = userService.page(pageParam, wrapper);
        return Resp.ok().data("items", pageModel.getRecords()).data("total", pageModel.getTotal());
    }

    @ApiOperation(value = "新增管理用户")
    @PostMapping("/save")
    public Resp save(@RequestBody User user) {
        user.setPassword(MD5.encrypt(user.getPassword()));
        userService.save(user);
        return Resp.ok();
    }

    @ApiOperation(value = "修改管理用户")
    @PutMapping("/update")
    public Resp updateById(@RequestBody User user) {
        userService.updateById(user);
        return Resp.ok();
    }

    @ApiOperation(value = "删除管理用户")
    @DeleteMapping("/remove/{id}")
    public Resp remove(@PathVariable String id) {
        userService.removeById(id);
        return Resp.ok();
    }

    @ApiOperation(value = "根据id列表删除管理用户")
    @DeleteMapping("/batchRemove")
    public Resp batchRemove(@RequestBody List<String> idList) {
        userService.removeByIds(idList);
        return Resp.ok();
    }

    @ApiOperation(value = "根据用户获取角色数据")
    @GetMapping("/toAssign/{userId}")
    public Resp toAssign(@PathVariable String userId) {
        Map<String, Object> roleMap = roleService.findRoleByUserId(userId);
        return Resp.ok().data(roleMap);
    }

    @ApiOperation(value = "根据用户分配角色")
    @PostMapping("/doAssign")
    public Resp doAssign(@RequestParam String userId,@RequestParam String[] roleId) {
        roleService.saveUserRoleRealtionShip(userId,roleId);
        return Resp.ok();
    }
    @GetMapping("/get/{userId}")
    public Resp getByUserID(@PathVariable("userId") String userId){
        User user = userService.getById(userId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("username",user.getUsername());
        map.put("nickName",user.getNickName());
        map.put("password","*请重新输入密码*");
        return Resp.ok().data("item",map);


    }

}

