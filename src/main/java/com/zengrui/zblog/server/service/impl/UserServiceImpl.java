package com.zengrui.zblog.server.service.impl;

import com.zengrui.zblog.common.exception.BusinessException;
import com.zengrui.zblog.common.result.Result;
import com.zengrui.zblog.pojo.dto.UserLoginDTO;
import com.zengrui.zblog.pojo.dto.UserRegisterDTO;
import com.zengrui.zblog.pojo.dto.UserUpdateDTO;
import com.zengrui.zblog.pojo.entity.User;
import com.zengrui.zblog.pojo.vo.UserLoginVO;
import com.zengrui.zblog.pojo.vo.UserViewVO;
import com.zengrui.zblog.server.mapper.UserMapper;
import com.zengrui.zblog.server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(UserRegisterDTO userRegisterDTO) {
        log.info("开始注册"+userRegisterDTO.toString());

        boolean isUserNameExists = userMapper.isUserNameExists(userRegisterDTO.getUsername());
        if (isUserNameExists) {
            throw new BusinessException("用户名重复");
        }
        //写入数据库
        LocalDateTime now = LocalDateTime.now();
        User user = User.builder()
                .username(userRegisterDTO.getUsername())
                .password(userRegisterDTO.getPassword())
                .createTime(now)
                .updateTime(now)
                .build();


        userMapper.insert(user);
    }

    @Override
    public UserLoginVO login(UserLoginDTO userLoginDTO) {
        //查询用户名密码是否正确
        User user = User.builder()
                .username(userLoginDTO.getUsername())
                .password(userLoginDTO.getPassword())
                .build();
        //全局异常处理
        Boolean isUserNameExists = userMapper.isUserNameExists(userLoginDTO.getUsername());
        if (!isUserNameExists) {
            throw new BusinessException("用户名不存在");
        }
        Boolean isUserPasswordCorrect = userMapper.isUserPasswordCorrect(user);
        if (!isUserPasswordCorrect) {
            throw new BusinessException("密码错误");
        }
        //TODO 生成jwt令牌
        String token = "114514暂时没有校验";
        /*Map<String,Object> claims = new HashMap<>();
        Long id = userMapper.getUserIdByUserNameAndPassword(user);
        claims.put("id",id);
        claims.put("userName", userLoginDTO.getUserName());
        claims.put("password", userLoginDTO.getPassword());
        String token = JwtUtil.createJwt(jwtProperties.getSecret(),claims,jwtProperties.getTtl());
        log.info(("当前用户的token是"+token));*/

        //构造loginVO
        User userOut = userMapper.getUserByUserNameAndPassword(user);
        return UserLoginVO.builder()
                .id(userOut.getId())
                .username(userOut.getUsername())
                .avatar(userOut.getAvatar())
                .token(token)
                .build();
    }

    @Override
    public UserViewVO view(Long userId) {
        return userMapper.getUserViewByUserId(userId);
    }

    @Override
    public void updateUserInfo(UserUpdateDTO userUpdateDTO) {
        userMapper.updateUserInfo(userUpdateDTO);
    }
}
