package com.zengrui.zblog.server.service;

import com.zengrui.zblog.pojo.dto.CommentAddDTO;
import com.zengrui.zblog.pojo.vo.CommentListVO;

import java.util.List;

public interface CommentService {
    void add(CommentAddDTO commentAddDTO);

    List<CommentListVO> list(Long id);
}
