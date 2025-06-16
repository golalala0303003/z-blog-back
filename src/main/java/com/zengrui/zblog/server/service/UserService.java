package com.zengrui.zblog.server.service;

import com.zengrui.zblog.pojo.dto.UserLoginDTO;
import com.zengrui.zblog.pojo.dto.UserRegisterDTO;
import com.zengrui.zblog.pojo.dto.UserUpdateDTO;
import com.zengrui.zblog.pojo.vo.UserLoginVO;
import com.zengrui.zblog.pojo.vo.UserViewVO;

public interface UserService {
    void register(UserRegisterDTO userRegisterDTO);

    UserLoginVO login(UserLoginDTO userLoginDTO);

    UserViewVO view(Long userId);

    void updateUserInfo(UserUpdateDTO userUpdateDTO);
}
