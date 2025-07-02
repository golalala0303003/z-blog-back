package com.zengrui.zblog.pojo.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class BlogListVO implements Serializable {
    private Long id;
    private String title;
    private String contentAbstract;
    private String cover;
    private String author;
    private Integer likeCount;
    private Integer commentCount;
    private LocalDateTime createTime;
}
