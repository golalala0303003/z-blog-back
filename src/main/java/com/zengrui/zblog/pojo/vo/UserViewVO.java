package com.zengrui.zblog.pojo.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserViewVO {
    private Long id;
    private String username;
    private String avatar;
}
