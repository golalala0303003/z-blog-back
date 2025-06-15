package com.zengrui.zblog.pojo.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginVO {
    private Long id;
    private String username;
    private String avatar;
    private String token;//jwt令牌
}
