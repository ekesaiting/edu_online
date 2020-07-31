package com.feng.vodservice.service.impl;

import cn.hutool.core.util.StrUtil;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.feng.commonutils.ResultCodeEnum;
import com.feng.servicebase.exception.BasicException;
import com.feng.vodservice.service.VodService;
import com.feng.vodservice.utils.AliyunVodPropertiesUtil;
import com.feng.vodservice.utils.VodClientInitUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author shf
 * @Date 2020/7/20 11:59
 */
@Service
public class VodServiceImpl  implements VodService {
    @Autowired
    private AliyunVodPropertiesUtil vodProperties;
    @Override
    public String uploadVideo(MultipartFile file)  {
        String accessKeyId=vodProperties.getKeyId();
        String accessKeySecret=vodProperties.getKeySecret();
        String fileName=file.getOriginalFilename();
        String title=fileName.substring(0,fileName.lastIndexOf("."));

        InputStream inputStream;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
          throw new BasicException(ResultCodeEnum.FILE_UPLOAD_ERROR);
        }
        UploadStreamRequest request = new UploadStreamRequest(accessKeyId, accessKeySecret, title, fileName, inputStream);
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);
        String videoId = response.getVideoId();
        if (videoId==null){
            throw new BasicException(ResultCodeEnum.FILE_UPLOAD_ERROR);
        }
        return videoId;
    }

    @Override
    public void deleteById(String vodId) {
        DefaultAcsClient client = null;
        try {
            client = VodClientInitUtil.initVodClient(
                    vodProperties.getKeyId(),
                    vodProperties.getKeySecret());
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(vodId);
            DeleteVideoResponse response = client.getAcsResponse(request);
        } catch (ClientException e) {
            throw new BasicException(ResultCodeEnum.FILE_DELETE_ERROR);
        }
    }

    @Override
    public void deleteVodBatch(List<String> vodIdList) {
        DefaultAcsClient client = null;
        try {
            client = VodClientInitUtil.initVodClient(
                    vodProperties.getKeyId(),
                    vodProperties.getKeySecret());
            DeleteVideoRequest request = new DeleteVideoRequest();
            String joinStr = StrUtil.join(",", vodIdList.toArray());
            request.setVideoIds(joinStr);
            DeleteVideoResponse response = client.getAcsResponse(request);
        } catch (ClientException e) {
            throw new BasicException(ResultCodeEnum.FILE_DELETE_ERROR);
        }
    }

    @Override
    public String getPlayAuth(String videoId) {
        DefaultAcsClient client = null;
        String playAuth=null;
        try {
            client=VodClientInitUtil.initVodClient(
                    vodProperties.getKeyId(),
                    vodProperties.getKeySecret());
            GetVideoPlayAuthRequest videoPlayAuthRequest = new GetVideoPlayAuthRequest();
            videoPlayAuthRequest.setVideoId(videoId);
            GetVideoPlayAuthResponse response = client.getAcsResponse(videoPlayAuthRequest);
            playAuth = response.getPlayAuth();
        } catch (ClientException e) {
            e.printStackTrace();
            throw  new BasicException(ResultCodeEnum.UNKNOWN_REASON);
        }
        return playAuth;
    }

}
