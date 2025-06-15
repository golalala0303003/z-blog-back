package com.zengrui.zblog.pojo.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class User {
    private Long id;             // 用户ID，自增主键

    private String username;     // 用户名，唯一，作为登录账号

    private String password;     // 用户密码

    private String avatar;       // 用户头像URL地址，可选

    private String email;        // 邮箱，可选

    private String phone;        // 手机号，可选

    private LocalDateTime createTime;  // 账号创建时间

    private LocalDateTime updateTime;  // 最近更新时间
}
