package com.zengrui.zblog.server.mapper;

import com.zengrui.zblog.pojo.entity.Blog;
import com.zengrui.zblog.pojo.vo.BlogListVO;
import com.zengrui.zblog.pojo.vo.ReadBlogVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface BlogMapper {
    void insert(Blog blog);

    ReadBlogVO getBlogByBlogId(Long id);

    List<BlogListVO> getAllBlogs();

    List<BlogListVO> getMineBlogs(Long id);

    @Update("update blog set comment_count = comment_count + 1 where id = #{blogId}")
    void addComment(Long blogId);
}