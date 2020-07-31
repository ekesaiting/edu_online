package com.feng.cmsservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.feng.cmsservice.entity.CrmBanner;
import com.feng.cmsservice.mapper.CrmBannerMapper;
import com.feng.cmsservice.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author shf
 * @since 2020-07-21
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Override
    @Cacheable(key = "'getAllBanner'",value = "banner")
    public List<CrmBanner> getAllBanner() {
        QueryWrapper<CrmBanner> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_modified");
        queryWrapper.last("limit 2");
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    @CacheEvict(value = "banner",allEntries = true)
    public boolean updateBannerById(CrmBanner banner) {
        int res = baseMapper.updateById(banner);
        return res > 0;
    }

    @Override
    @CacheEvict(value = "banner",allEntries = true)
    public boolean saveBanner(CrmBanner banner) {
        int res = baseMapper.insert(banner);
        return res>0;
    }

    @Override
    @CacheEvict(value = "banner",allEntries = true)
    public boolean deleteBannerById(String bannerId) {
        int res = baseMapper.deleteById(bannerId);
        return res>0;
    }
}
