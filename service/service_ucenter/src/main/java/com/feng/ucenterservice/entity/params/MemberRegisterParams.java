package com.feng.ucenterservice.entity.params;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author shf
 * @Date 2020/7/22 22:38
 */
@Data
public class MemberRegisterParams {
    @NotBlank(message = "手机号不能为空")
    private String mobile;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotBlank(message = "验证码不能为空")
    private String code;
    @NotBlank(message = "昵称不能为空")
    private String nickname;
}
