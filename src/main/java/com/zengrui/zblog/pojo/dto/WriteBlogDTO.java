package com.zengrui.zblog.pojo.dto;

import lombok.Data;

@Data
public class WriteBlogDTO {
    private String title;
    private String content;
    private String cover;
    private Long authorId;
}
