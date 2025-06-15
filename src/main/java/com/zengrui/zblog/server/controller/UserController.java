package com.zengrui.zblog.server.controller;

import com.zengrui.zblog.common.result.Result;
import com.zengrui.zblog.pojo.dto.UserLoginDTO;
import com.zengrui.zblog.pojo.dto.UserRegisterDTO;
import com.zengrui.zblog.pojo.entity.User;
import com.zengrui.zblog.pojo.vo.UserLoginVO;
import com.zengrui.zblog.server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@RequestBody UserRegisterDTO userRegisterDTO) {
        log.info("用户注册"+userRegisterDTO.toString());
        userService.register(userRegisterDTO);
        return Result.success();
    }

    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("用户登录"+userLoginDTO.toString());
        UserLoginVO userLoginVO = userService.login(userLoginDTO);
        return Result.success(userLoginVO);
    }
}
