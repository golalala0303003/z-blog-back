package com.zengrui.zblog.pojo.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReadBlogVO {
    private String title;
    private String content;
    private String cover;
    private Long author;
    private String avatar;
    private LocalDateTime createTime;
}
