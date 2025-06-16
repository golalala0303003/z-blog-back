package com.zengrui.zblog.server.mapper;

import com.zengrui.zblog.pojo.dto.CommentAddDTO;
import com.zengrui.zblog.pojo.entity.Comment;
import com.zengrui.zblog.pojo.vo.CommentListVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Insert("insert into comment(blog_id, author, content, create_time) values (#{blogId},#{author},#{content},#{createTime}) ")
    void add(Comment comment);

    List<CommentListVO> list(Long id);
}
