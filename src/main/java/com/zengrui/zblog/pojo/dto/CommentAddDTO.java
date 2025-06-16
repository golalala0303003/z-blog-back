package com.zengrui.zblog.pojo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentAddDTO {
    private Long blogId;
    private Long authorId;
    private String content;
}
