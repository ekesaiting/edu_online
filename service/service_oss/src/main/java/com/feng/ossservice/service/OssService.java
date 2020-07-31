package com.feng.ossservice.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author shf
 * @Date 2020/7/13 21:28
 */
public interface OssService {
    String uploadAvatarFile(MultipartFile file);
}
