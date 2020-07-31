package com.feng.ucenterservice.controller;


import com.feng.commonutils.JwtUtils;
import com.feng.commonutils.Resp;
import com.feng.commonutils.ordervo.UcenterMemberOrder;
import com.feng.ucenterservice.entity.UcenterMember;
import com.feng.ucenterservice.entity.params.MemberLoginParams;
import com.feng.ucenterservice.entity.params.MemberRegisterParams;
import com.feng.ucenterservice.service.UcenterMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author shf
 * @since 2020-07-22
 */
@RestController
@RequestMapping("/ucenterService/member")
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService memberService;

    @PostMapping("/login")
    public Resp login(@RequestBody @Valid MemberLoginParams loginParams){
//        TODO 放弃MD5加密，修改加密方式或者更换框架为Spring Security
        String token=memberService.login(loginParams);
        return Resp.ok().data("token",token);
    }
    @PostMapping("/register")
    public Resp register(@RequestBody @Valid MemberRegisterParams registerParams){
        int res = memberService.register(registerParams);
        return res>0?Resp.ok():Resp.error();
    }
    @GetMapping("/getMemberInfo")
    public Resp getMemberInfo(HttpServletRequest servletRequest){
        String id = JwtUtils.getMemberIdByJwtToken(servletRequest);
        UcenterMember member = memberService.getById(id);
        return Resp.ok().data("memberInfo",member);
    }
    @GetMapping("/getMemberInfoForOrder/{memberId}")
    public UcenterMemberOrder getMemberInfoForOrder(@PathVariable("memberId") String memberId ){
        UcenterMember member = memberService.getById(memberId);
        UcenterMemberOrder memberOrder = new UcenterMemberOrder();
        BeanUtils.copyProperties(member,memberOrder);
        return memberOrder;
    }
    @GetMapping("/getDailyRegister/{day}")
    public Resp getDailyRegister(@PathVariable("day") String day){
        int memberCount=memberService.getDailyRegister(day);
        return Resp.ok().data("registerCount",memberCount);
    }

}

