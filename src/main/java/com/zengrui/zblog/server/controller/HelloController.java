package com.zengrui.zblog.server.controller;


import com.zengrui.zblog.common.result.Result;
import com.zengrui.zblog.pojo.dto.HelloDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testhello")
@CrossOrigin
public class HelloController {
    @GetMapping("/hello")
    public Result hello() {
        HelloDTO helloDTO = new HelloDTO("测试一下喵");
        return Result.success(helloDTO);
    }
}
