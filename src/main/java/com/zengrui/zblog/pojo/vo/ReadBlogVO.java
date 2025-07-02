package com.zengrui.zblog.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ReadBlogVO implements Serializable {
    private String title;
    private String content;
    private String cover;
    private String author;
    private String avatar;
    private LocalDateTime createTime;
}
