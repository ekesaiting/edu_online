package com.feng.eduservice.controller;

import com.feng.commonutils.Resp;
import com.feng.eduservice.entity.params.LoginParams;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @Author shf
 * @Date 2020/7/11 12:56
 */
@Api(tags = "后台登入")
@RestController
@RequestMapping("/eduService/user")
public class EduLoginController {
    @PostMapping("/login")
    public Resp login(@RequestBody LoginParams query){
        return Resp.ok().data("token","abc");
    }
    @GetMapping("/info")
    public Resp info(String token){
        HashMap<String, Object> map = new HashMap<>();
        map.put("roles",new String[]{"user"});
        map.put("name","shf");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Resp.ok().data(map);
    }
}
