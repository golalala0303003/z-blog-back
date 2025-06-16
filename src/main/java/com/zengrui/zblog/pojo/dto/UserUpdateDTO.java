package com.zengrui.zblog.pojo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserUpdateDTO {
    private Long id;
    private String username;
    private String avatar;
    private String password;
}
