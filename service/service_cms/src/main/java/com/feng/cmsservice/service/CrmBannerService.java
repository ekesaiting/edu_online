package com.feng.cmsservice.service;

import com.feng.cmsservice.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author shf
 * @since 2020-07-21
 */
public interface CrmBannerService extends IService<CrmBanner> {

    List<CrmBanner> getAllBanner();

    boolean updateBannerById(CrmBanner banner);

    boolean saveBanner(CrmBanner banner);

    boolean deleteBannerById(String bannerId);
}
