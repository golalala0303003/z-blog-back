package com.zengrui.zblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ZBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZBlogApplication.class, args);
    }

}
