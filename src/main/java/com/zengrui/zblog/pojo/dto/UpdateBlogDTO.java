package com.zengrui.zblog.pojo.dto;

import lombok.Data;

@Data
public class UpdateBlogDTO {
    private Long id;
    private String title;
    private String content;
    private String cover;
    private Long authorId;
}
