package com.feng.cmsservice.controller;

import com.feng.cmsservice.entity.CrmBanner;
import com.feng.cmsservice.service.CrmBannerService;
import com.feng.commonutils.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author shf
 * @Date 2020/7/21 15:58
 */
@RestController
@RequestMapping("/cmsService/bannerFront")
public class BannerFrontController {
    @Autowired
    private CrmBannerService bannerService;

    @GetMapping("/getAllBanner")
    public Resp getAllBanner(){
        List<CrmBanner> bannerList = bannerService.getAllBanner();
        return Resp.ok().data("bannerList",bannerList);
    }
}
