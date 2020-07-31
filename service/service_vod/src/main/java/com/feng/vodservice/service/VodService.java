package com.feng.vodservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author shf
 * @Date 2020/7/20 11:58
 */
public interface VodService {

    String uploadVideo(MultipartFile file);

    void deleteById(String vodId);

    void deleteVodBatch(List<String> vodIdList);

    String getPlayAuth(String videoId);
}
