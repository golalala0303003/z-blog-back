package com.zengrui.zblog.server.controller;


import com.zengrui.zblog.common.exception.BusinessException;
import com.zengrui.zblog.common.result.Result;
import com.zengrui.zblog.pojo.dto.WriteBlogDTO;
import com.zengrui.zblog.server.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
}
