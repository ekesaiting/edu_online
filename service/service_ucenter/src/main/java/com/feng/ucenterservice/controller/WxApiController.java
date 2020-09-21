package com.feng.ucenterservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.feng.commonutils.JwtUtils;
import com.feng.commonutils.Resp;
import com.feng.commonutils.ResultCodeEnum;
import com.feng.servicebase.exception.BasicException;
import com.feng.ucenterservice.entity.UcenterMember;
import com.feng.ucenterservice.service.UcenterMemberService;
import com.feng.ucenterservice.utils.HttpClientUtils;
import com.feng.ucenterservice.utils.WeixingOAuth2Properties;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * @Author shf
 * @Date 2020/7/24 14:02
 */
@Controller
public class WxApiController {
    @Autowired
    private WeixingOAuth2Properties wxProperties;
    @Autowired
    private UcenterMemberService memberService;

    //此为固定地址，用户微信确认后回调尚硅谷填写的固定地址，再由尚硅谷的服务器重定向到此地址
    @GetMapping("/api/ucenter/wx/callback")
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
            //重定向到前端页面首页，并附带token信息，前端携带token访问正常登入接口得到用户信息
            String url="http://localhost:3000";
            String jwtToken = JwtUtils.getJwtToken(memberRes.getId(), memberRes.getNickname());
            url=url+"?token="+jwtToken;
            return "redirect:"+url;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BasicException(ResultCodeEnum.UNKNOWN_REASON);
        }
    }

    @GetMapping("/ucenterService/member/wxLogin")
    @ResponseBody
    public Resp getWxCode(){
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
        return Resp.ok().data("codeUrl",url);
    }
}
