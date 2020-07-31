package com.feng.ucenterservice.mapper;

import com.feng.ucenterservice.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author shf
 * @since 2020-07-22
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    int getDailyRegister(String day);
}
