package com.zengrui.zblog.server.service;

import com.zengrui.zblog.pojo.dto.BlogLikeDTO;
import com.zengrui.zblog.pojo.dto.BlogListDTO;
import com.zengrui.zblog.pojo.dto.UpdateBlogDTO;
import com.zengrui.zblog.pojo.dto.WriteBlogDTO;
import com.zengrui.zblog.pojo.vo.BlogListVO;
import com.zengrui.zblog.pojo.vo.ReadBlogVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BlogService {
    String uploadToOSS(MultipartFile file);

    void writeBlog(WriteBlogDTO writeBlogDTO);

    ReadBlogVO readBlog(Long id);

    List<BlogListVO> blogList(BlogListDTO blogListDTO);

    void like(BlogLikeDTO blogLikeDTO);

    void delete(Long id);

    void update(UpdateBlogDTO updateBlogDTO);
}
