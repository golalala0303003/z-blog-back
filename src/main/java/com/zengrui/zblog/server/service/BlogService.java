package com.zengrui.zblog.server.service;

import com.zengrui.zblog.pojo.dto.WriteBlogDTO;
import org.springframework.web.multipart.MultipartFile;

public interface BlogService {
    String uploadToOSS(MultipartFile file);

    void writeBlog(WriteBlogDTO writeBlogDTO);
}
