package com.zengrui.zblog.pojo.vo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentListVO {
    private Long id;
    private Long blogId;
    private String author;
    private String content;
    private LocalDateTime createTime;
}
