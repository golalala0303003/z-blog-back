package com.zengrui.zblog.pojo.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class Blog {
    private Long id;
    private String title;
    private String content;
    private String cover;
    private Long authorId;
    private Integer likeCount;
    private Integer commentCount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
