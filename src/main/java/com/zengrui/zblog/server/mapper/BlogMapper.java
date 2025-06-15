package com.zengrui.zblog.server.mapper;

import com.zengrui.zblog.pojo.entity.Blog;
import com.zengrui.zblog.pojo.vo.ReadBlogVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlogMapper {
    void insert(Blog blog);

    ReadBlogVO getBlogByBlogId(Long id);
}