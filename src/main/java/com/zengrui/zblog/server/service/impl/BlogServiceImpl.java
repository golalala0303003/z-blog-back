package com.zengrui.zblog.server.service.impl;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.zengrui.zblog.common.properties.AliOssProperties;
import com.zengrui.zblog.pojo.dto.BlogLikeDTO;
import com.zengrui.zblog.pojo.dto.BlogListDTO;
import com.zengrui.zblog.pojo.dto.UpdateBlogDTO;
import com.zengrui.zblog.pojo.dto.WriteBlogDTO;
import com.zengrui.zblog.pojo.entity.Blog;
import com.zengrui.zblog.pojo.vo.BlogListVO;
import com.zengrui.zblog.pojo.vo.ReadBlogVO;
import com.zengrui.zblog.server.mapper.BlogMapper;
import com.zengrui.zblog.server.service.BlogService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class BlogServiceImpl implements BlogService {
    @Autowired
    private AliOssProperties aliOssProperties;

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public String uploadToOSS(MultipartFile file) {
        log.info("已经收到图片");
        //创建 OSS 客户端
        OSS ossClient = new OSSClientBuilder().build(
                aliOssProperties.getEndpoint(),
                aliOssProperties.getAccessKeyId(),
                aliOssProperties.getAccessKeySecret()
        );

        try {
            //获取原始文件名后缀
            String originalFilename = file.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            //构造唯一文件名
            String fileName = UUID.randomUUID().toString() + suffix;
            //拼接路径
            String filePath = aliOssProperties.getDir() + fileName;
            ossClient.putObject(
                    aliOssProperties.getBucketName(),
                    filePath,
                    file.getInputStream()
            );
            //返回URL
            return "https://" + aliOssProperties.getBucketName() + "." + aliOssProperties.getEndpoint() + "/" + filePath;
        } catch (IOException e) {
            throw new RuntimeException("文件上传失败", e);
        } finally {
            ossClient.shutdown();
        }
    }

    @Override
    public void writeBlog(WriteBlogDTO writeBlogDTO) {
        log.info(writeBlogDTO.toString());
        LocalDateTime now = LocalDateTime.now();
        Blog blog = Blog.builder()
                .title(writeBlogDTO.getTitle())
                .cover(writeBlogDTO.getCover())
                .content(writeBlogDTO.getContent())
                .authorId(writeBlogDTO.getAuthorId())
                .createTime(now)
                .updateTime(now)
                .build();
        blogMapper.insert(blog);
    }

    @Override
    public ReadBlogVO readBlog(Long id) {
        return blogMapper.getBlogByBlogId(id);
    }

    @Override
    public List<BlogListVO> blogList(BlogListDTO blogListDTO) {
        //处理推荐 就是所有都让前端拉取回去
        if(Objects.equals(blogListDTO.getType(), "recommend")){
            return blogMapper.getAllBlogs();
        }

        if(Objects.equals(blogListDTO.getType(), "mine")){
            return blogMapper.getMineBlogs(blogListDTO.getId());
        }

        return null;
    }

    @Override
    public void like(BlogLikeDTO blogLikeDTO) {
        if(blogLikeDTO.getLike()) {
            blogMapper.addLikeCount(blogLikeDTO);
        }else{
            blogMapper.subLikeCount(blogLikeDTO);
        }

    }

    @Override
    public void delete(Long id) {
        blogMapper.deleteById(id);
    }

    @Override
    public void update(UpdateBlogDTO updateBlogDTO) {
        blogMapper.update(updateBlogDTO);
    }
}
