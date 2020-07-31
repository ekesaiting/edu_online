package com.feng.aclservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.feng.aclservice.service.IndexService;
import com.feng.commonutils.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author shf
 * @Date 2020/7/30 16:44
 */
@RestController
@RequestMapping("/aclService/index")
public class IndexController {
    @Autowired
    private IndexService indexService;

    /**
     * 根据token获取用户信息
     */
    @GetMapping("info")
    public Resp info(){
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String, Object> userInfo = indexService.getUserInfo(username);
        return Resp.ok().data(userInfo);
    }

    /**
     * 获取菜单
     */
    @GetMapping("menu")
    public Resp getMenu(){
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<JSONObject> permissionList = indexService.getMenu(username);
        return Resp.ok().data("permissionList", permissionList);
    }

    @PostMapping("logout")
    public Resp logout(){
        return Resp.ok();
    }
}
