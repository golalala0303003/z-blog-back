package com.zengrui.zblog.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Long id;
    private Long blogId;
    private String author;
    private String content;
    private LocalDateTime createTime;
}
