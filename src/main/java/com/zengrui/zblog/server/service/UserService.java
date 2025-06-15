package com.zengrui.zblog.server.service;

import com.zengrui.zblog.pojo.dto.UserLoginDTO;
import com.zengrui.zblog.pojo.dto.UserRegisterDTO;
import com.zengrui.zblog.pojo.vo.UserLoginVO;

public interface UserService {
    void register(UserRegisterDTO userRegisterDTO);

    UserLoginVO login(UserLoginDTO userLoginDTO);
}
