package com.feng.ucenterservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.feng.commonutils.JwtUtils;
import com.feng.commonutils.ResultCodeEnum;
import com.feng.servicebase.exception.BasicException;
import com.feng.ucenterservice.entity.UcenterMember;
import com.feng.ucenterservice.service.UcenterMemberService;
import com.feng.ucenterservice.utils.HttpClientUtils;
import com.feng.ucenterservice.utils.WeixingOAuth2Properties;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * @Author shf
 * @Date 2020/7/24 14:02
 */
@Controller
@RequestMapping("/api/ucenter/wx")
public class WxApiController {
    @Autowired
    private WeixingOAuth2Properties wxProperties;
    @Autowired
    private UcenterMemberService memberService;

    @GetMapping("callback")
    public String callback(String code,String state){
        //向认证服务器发送请求换取access_token
        String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=%s" +
                "&secret=%s" +
                "&code=%s" +
                "&grant_type=authorization_code";

        String accessTokenUrl=String.format(
                baseAccessTokenUrl,
                wxProperties.getAppId(),
                wxProperties.getAppSecret(),
                code);

        try {
            String accessTokenInfo = HttpClientUtils.get(accessTokenUrl);
            Gson gson = new Gson();
            HashMap jsonMap = gson.fromJson(accessTokenInfo, HashMap.class);
            String accessToken = (String)jsonMap.get("access_token");
            String openid = (String)jsonMap.get("openid");

            QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("openid",openid);
            UcenterMember memberRes = memberService.getOne(queryWrapper);
           //如果数据库中不存在信息则自动注册
            if (memberRes==null){
                String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                        "?access_token=%s" +
                        "&openid=%s";

                String userInfoUrl=String.format(baseUserInfoUrl,accessToken,openid);
                String userInfo = HttpClientUtils.get(userInfoUrl);
                HashMap userInfoMap = gson.fromJson(userInfo, HashMap.class);
                String nickName=(String) userInfoMap.get("nickname");
                String avatar=(String) userInfoMap.get("headimgurl");

                memberRes= new UcenterMember();
                memberRes.setOpenid(openid);
                memberRes.setNickname(nickName);
                memberRes.setAvatar(avatar);
                memberService.save(memberRes);
            }
            String url="http://localhost:3000";
            String jwtToken = JwtUtils.getJwtToken(memberRes.getId(), memberRes.getNickname());
            url=url+"?token="+jwtToken;
            return "redirect:"+url;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BasicException(ResultCodeEnum.UNKNOWN_REASON);
        }
    }

    @GetMapping("/login")
    public String getWxCode(){
        // 微信开放平台授权baseUrl
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";
        String encodeUrl = null;
        try {
            encodeUrl = URLEncoder.encode(wxProperties.getRedirectUrl(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url=String.format(baseUrl,wxProperties.getAppId(),encodeUrl,"ekst");
        return "redirect:"+url;
    }
}
