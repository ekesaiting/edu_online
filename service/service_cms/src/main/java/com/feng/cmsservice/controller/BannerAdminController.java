package com.feng.cmsservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feng.cmsservice.entity.CrmBanner;
import com.feng.cmsservice.service.CrmBannerService;
import com.feng.commonutils.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author shf
 * @Date 2020/7/21 15:58
 */
@RestController
@RequestMapping("/cmsService/bannerAdmin")
public class BannerAdminController {
    @Autowired
    private CrmBannerService bannerService;

    @GetMapping("/pageBanner/{page}/{size}")
    public Resp pageBanner(@PathVariable("page") long page,@PathVariable("size") long size){
        Page<CrmBanner> bannerPage = new Page<>(page,size);
        bannerService.page(bannerPage);
        List<CrmBanner> records = bannerPage.getRecords();
        long total = bannerPage.getTotal();
        return Resp.ok().data("total",total).data("bannerList",records);
    }
    @GetMapping("/{bannerId}")
    public Resp getBannerById(@PathVariable("bannerId") String id){
        CrmBanner banner = bannerService.getById(id);
        return Resp.ok().data("bannerInfo",banner);
    }

    @PutMapping("/updateBanner")
    public Resp updateBanner(@RequestBody CrmBanner banner){
        boolean res= bannerService.updateBannerById(banner);
        return res?Resp.ok():Resp.error();
    }
    @PostMapping("/addBanner")
    public Resp addBanner(@RequestBody CrmBanner banner){
        boolean res = bannerService.saveBanner(banner);
        return  res?Resp.ok():Resp.error();
    }
    @DeleteMapping("/deleteBanner/{bannerId}")
    public Resp updateBanner(@PathVariable("bannerId") String bannerId){
        boolean res = bannerService.deleteBannerById(bannerId);
        return  res?Resp.ok():Resp.error();
    }
}
