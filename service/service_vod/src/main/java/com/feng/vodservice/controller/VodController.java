package com.feng.vodservice.controller;

import com.feng.commonutils.Resp;
import com.feng.vodservice.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author shf
 * @Date 2020/7/20 11:57
 */
@RequestMapping("/vodService")
@RestController
public class VodController {
    @Autowired
    private VodService vodService;
    @PostMapping("/uploadVideo")
    public Resp uploadVideo(MultipartFile file){
        String videoId=vodService.uploadVideo(file);
        return Resp.ok().data("videoId",videoId);
    }
    @DeleteMapping("/deleteVod/{vodId}")
    public Resp deleteById(@PathVariable("vodId") String vodId){
        vodService.deleteById(vodId);
        return Resp.ok();
    }
    @DeleteMapping("/deleteVodBatch")
    public Resp deleteByIdBatch(@RequestParam("vodIdList") List<String> vodIdList){
        vodService.deleteVodBatch(vodIdList);
        return Resp.ok();
    }
    @GetMapping("/getPlayAuth/{vodId}")
    public Resp getPlayAuth(@PathVariable("vodId") String vodId){
        String auth=vodService.getPlayAuth(vodId);
        return  Resp.ok().data("playAuth",auth);
    }
}
