package com.zengrui.zblog.pojo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BlogLikeDTO {
    private Long blogId;
    private Boolean like;
}
