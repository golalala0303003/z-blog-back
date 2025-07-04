package com.zengrui.zblog.server.service.impl;

import com.zengrui.zblog.common.exception.BusinessException;
import com.zengrui.zblog.common.properties.JwtProperties;

import com.zengrui.zblog.common.utils.JwtUtil;
import com.zengrui.zblog.pojo.dto.UserLoginDTO;
import com.zengrui.zblog.pojo.dto.UserRegisterDTO;
import com.zengrui.zblog.pojo.dto.UserUpdateDTO;
import com.zengrui.zblog.pojo.entity.User;
import com.zengrui.zblog.pojo.vo.ReadBlogVO;
import com.zengrui.zblog.pojo.vo.UserLoginVO;
import com.zengrui.zblog.pojo.vo.UserViewVO;
import com.zengrui.zblog.server.mapper.UserMapper;
import com.zengrui.zblog.server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void register(UserRegisterDTO userRegisterDTO) {
        log.debug("开始注册{}",userRegisterDTO.toString());

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

        //生成jwt
        Map<String,Object> claims = new HashMap<>();
        Long id = userMapper.getUserIdByUserNameAndPassword(user);
        claims.put("id",id);
        claims.put("username", userLoginDTO.getUsername());
        String token = JwtUtil.createJwt(jwtProperties.getSecretKey(),jwtProperties.getExpirationTime(),claims);
        log.info("已经为id为{}的用户生成了token{}",id,token);

        //redis存储
        String cacheKey = "userToken::" + id;
        String usernameGetFromRedis = stringRedisTemplate.opsForValue().get(cacheKey);
        if(usernameGetFromRedis == null){
            log.info("查询token缓存未命中");
            stringRedisTemplate.opsForValue().set(cacheKey, userLoginDTO.getUsername());
        }
        log.info("查询token缓存命中");

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
