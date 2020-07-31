package com.feng.ucenterservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.feng.commonutils.JwtUtils;
import com.feng.commonutils.MD5;
import com.feng.commonutils.ResultCodeEnum;
import com.feng.servicebase.exception.BasicException;
import com.feng.servicebase.exception.MemberException;
import com.feng.servicebase.exception.enums.MemberExceptionEnum;
import com.feng.ucenterservice.entity.UcenterMember;
import com.feng.ucenterservice.entity.params.MemberLoginParams;
import com.feng.ucenterservice.entity.params.MemberRegisterParams;
import com.feng.ucenterservice.mapper.UcenterMemberMapper;
import com.feng.ucenterservice.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author shf
 * @since 2020-07-22
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String login(MemberLoginParams loginParams) {
        QueryWrapper<UcenterMember> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.eq("mobile", loginParams.getMobile());
        UcenterMember member = baseMapper.selectOne(memberQueryWrapper);
        //判断用户是否存在
        if (member == null) {
            throw new MemberException(MemberExceptionEnum.MEMBER_NOT_EXIST);
        }
        //判断密码
        if (!MD5.encrypt(loginParams.getPassword()).equals(member.getPassword())) {
            throw new MemberException(MemberExceptionEnum.PASSWORD_ERROR);
        }
        //判断是否禁用
        if (member.getIsDisabled()) {
            throw new MemberException(MemberExceptionEnum.MEMBER_IS_DISABLED);
        }
        return JwtUtils.getJwtToken(member.getId(), member.getNickname());
    }

    @Override
    @Transactional
    public int register(MemberRegisterParams registerParams) {
        QueryWrapper<UcenterMember> mobileQueryWrapper = new QueryWrapper<>();
        mobileQueryWrapper.eq("mobile",registerParams.getMobile());
        Integer count = baseMapper.selectCount(mobileQueryWrapper);
        if (count > 0) {
            throw new MemberException(MemberExceptionEnum.MEMBER_EXIST);
        }
        String code = redisTemplate.opsForValue().get(registerParams.getMobile());
        if (code == null) {
            throw new MemberException(MemberExceptionEnum.CODE_INVALIDATION);
        }
        if (!code.equals(registerParams.getCode())) {
            throw new MemberException(MemberExceptionEnum.CODE_ERROR);
        }
        UcenterMember ucenterMember = new UcenterMember();
        ucenterMember.setMobile(registerParams.getMobile());
        ucenterMember.setNickname(registerParams.getNickname());
        //TODO：更换加密方式
        ucenterMember.setPassword(MD5.encrypt(registerParams.getPassword()));
        return baseMapper.insert(ucenterMember);
    }

    @Override
    public int getDailyRegister(String day) {
       return baseMapper.getDailyRegister(day);
    }
}
