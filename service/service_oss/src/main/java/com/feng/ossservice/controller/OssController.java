package com.feng.ossservice.controller;

import com.feng.commonutils.Resp;
import com.feng.ossservice.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author shf
 * @Date 2020/7/13 21:28
 */
@RestController
@RequestMapping("/ossService")
public class OssController {
    @Autowired
    private OssService ossService;

    @PostMapping("/uploadAvatar")
    public Resp UploadAvatar(MultipartFile avatar){

        String filePath = ossService.uploadAvatarFile(avatar);
        return Resp.ok().data("filePath",filePath);
    }
    @PostMapping("/uploadCover")
    public Resp UploadCover(MultipartFile cover){

        String filePath = ossService.uploadAvatarFile(cover);
        return Resp.ok().data("filePath",filePath);
    }
}
