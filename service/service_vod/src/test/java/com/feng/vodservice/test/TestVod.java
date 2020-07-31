package com.feng.vodservice.test;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;

import java.util.List;

/**
 * @Author shf
 * @Date 2020/7/19 16:13
 */
public class TestVod {
    public static void main(String[] args) throws ClientException {
        String accessKey="LTAI4G7oF7ArQhQmR6vsfE3j";
        String  secret="ERTM6v5zalMyyfxFB9qGFmQKj7hAui";
        DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(accessKey,secret);
        GetVideoPlayAuthRequest authRequest = new GetVideoPlayAuthRequest();
        authRequest.setVideoId("6854efb7f7a443e3ad965e646f8b4199");
        GetVideoPlayAuthResponse response = client.getAcsResponse(authRequest);
        System.out.println(response.getPlayAuth());

    }
    public  static void getVideoUrl() throws ClientException {
        String accessKey="LTAI4G7oF7ArQhQmR6vsfE3j";
        String  secret="ERTM6v5zalMyyfxFB9qGFmQKj7hAui";
        DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(accessKey,secret);

        GetPlayInfoRequest request=new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();

        request.setVideoId("6854efb7f7a443e3ad965e646f8b4199");

        response=client.getAcsResponse(request);
        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        //播放地址
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
        }
        //Base信息
        System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
    }
}
