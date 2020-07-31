package com.feng.ucenterservice.entity.params;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Author shf
 * @Date 2020/7/22 22:00
 */
@Data
public class MemberLoginParams {
    @NotBlank(message = "账号不能为空")
    private String mobile;
    @NotBlank(message = "密码不能为空")
    private String password;
}
