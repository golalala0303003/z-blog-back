package com.zengrui.zblog.server.controller;


import com.zengrui.zblog.common.exception.BusinessException;
import com.zengrui.zblog.common.result.Result;
import com.zengrui.zblog.pojo.dto.BlogListDTO;
import com.zengrui.zblog.pojo.dto.WriteBlogDTO;
import com.zengrui.zblog.pojo.vo.BlogListVO;
import com.zengrui.zblog.pojo.vo.ReadBlogVO;
import com.zengrui.zblog.server.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @PostMapping("/upload")
    public Result<String> uploadImg(@RequestParam("file") MultipartFile file){
        if (file.isEmpty()) {
            throw new BusinessException("文件不能为空");
        }
        String url = blogService.uploadToOSS(file);
        return Result.success(url);
    }

    @PostMapping("/write")
    public Result writeBlog(@RequestBody WriteBlogDTO writeBlogDTO){
        blogService.writeBlog(writeBlogDTO);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<ReadBlogVO> readBlog(@PathVariable Long id){
        ReadBlogVO readBlogVO = blogService.readBlog(id);
        return Result.success(readBlogVO);
    }

    @PostMapping("/list")
    public Result<List<BlogListVO>> blogList(@RequestBody BlogListDTO blogListDTO){
        log.info(blogListDTO.toString());
        List<BlogListVO> listVOS = blogService.blogList(blogListDTO);
        return Result.success(listVOS);
    }
}
