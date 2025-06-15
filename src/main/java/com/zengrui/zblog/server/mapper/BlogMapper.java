package com.zengrui.zblog.server.mapper;

import com.zengrui.zblog.pojo.entity.Blog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlogMapper {
    void insert(Blog blog);
}
