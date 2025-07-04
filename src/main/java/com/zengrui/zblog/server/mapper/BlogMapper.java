package com.zengrui.zblog.server.mapper;

import com.zengrui.zblog.pojo.dto.BlogLikeDTO;
import com.zengrui.zblog.pojo.dto.UpdateBlogDTO;
import com.zengrui.zblog.pojo.entity.Blog;
import com.zengrui.zblog.pojo.vo.BlogListVO;
import com.zengrui.zblog.pojo.vo.ReadBlogVO;
import org.apache.ibatis.annotations.Delete;
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

    @Update("update blog set like_count = like_count + 1 where id = #{blogId}")
    void addLikeCount(BlogLikeDTO blogLikeDTO);

    @Update("update blog set like_count = like_count - 1 where id = #{blogId}")
    void subLikeCount(BlogLikeDTO blogLikeDTO);

    void update(UpdateBlogDTO updateBlogDTO);

    @Delete("delete from blog where id = #{id}")
    void deleteById(Long id);
}