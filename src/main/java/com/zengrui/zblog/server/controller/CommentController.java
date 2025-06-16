package com.zengrui.zblog.server.controller;

import com.zengrui.zblog.common.result.Result;
import com.zengrui.zblog.pojo.dto.CommentAddDTO;
import com.zengrui.zblog.pojo.vo.CommentListVO;
import com.zengrui.zblog.server.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public Result add(@RequestBody CommentAddDTO commentAddDTO) {
        log.info("commentAddDTO:{}", commentAddDTO);
        commentService.add(commentAddDTO);
        return Result.success();
    }


    @GetMapping("/list/{id}")
    public Result<List<CommentListVO>> getCommentList(@PathVariable Long id) {
        log.info("有人请求了评论区"+id);
        List<CommentListVO> listVOS = commentService.list(id);
        return Result.success(listVOS);
    }
}
