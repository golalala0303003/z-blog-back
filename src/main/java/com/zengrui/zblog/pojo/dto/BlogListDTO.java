package com.zengrui.zblog.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BlogListDTO implements Serializable {
    private Long id;
    private String type;
}
