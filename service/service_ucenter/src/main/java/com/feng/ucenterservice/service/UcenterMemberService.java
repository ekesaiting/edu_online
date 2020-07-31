package com.feng.ucenterservice.service;

import com.feng.ucenterservice.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.feng.ucenterservice.entity.params.MemberLoginParams;
import com.feng.ucenterservice.entity.params.MemberRegisterParams;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author shf
 * @since 2020-07-22
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(MemberLoginParams loginParams);

    int register(MemberRegisterParams registerParams);

    int getDailyRegister(String day);
}
