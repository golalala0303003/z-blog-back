package com.zengrui.zblog.server.service.impl;

import com.zengrui.zblog.pojo.dto.CommentAddDTO;
import com.zengrui.zblog.pojo.entity.Comment;
import com.zengrui.zblog.pojo.vo.CommentListVO;
import com.zengrui.zblog.pojo.vo.UserViewVO;
import com.zengrui.zblog.server.mapper.BlogMapper;
import com.zengrui.zblog.server.mapper.CommentMapper;
import com.zengrui.zblog.server.service.BlogService;
import com.zengrui.zblog.server.service.CommentService;
import com.zengrui.zblog.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private UserService userService;

    @Override
    public void add(CommentAddDTO commentAddDTO) {
        UserViewVO userViewVO = userService.view(commentAddDTO.getAuthorId());
        blogMapper.addComment(commentAddDTO.getBlogId());
        String username = userViewVO.getUsername();
        Comment comment = Comment.builder()
                .author(username)
                .content(commentAddDTO.getContent())
                .createTime(LocalDateTime.now())
                .blogId(commentAddDTO.getBlogId())
                .build();
        commentMapper.add(comment);
    }

    @Override
    public List<CommentListVO> list(Long id) {
        return commentMapper.list(id);
    }
}
