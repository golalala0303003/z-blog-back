package com.zengrui.zblog.server.interceptor;

import com.zengrui.zblog.common.exception.AuthenticationException;
import com.zengrui.zblog.common.exception.BusinessException;
import com.zengrui.zblog.common.properties.JwtProperties;
import com.zengrui.zblog.common.utils.JwtUtil;
import com.zengrui.zblog.server.mapper.UserMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.micrometer.common.lang.NonNullApi;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@NonNullApi
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        if (!(handler instanceof HandlerMethod)) {
            // 当前拦截到的不是 Controller 中的方法，比如资源请求、预检请求，直接放行 我服了！！！要注意这个！！！
            return true;
        }

        log.info("该请求触发了jwt拦截器-------!!!--------");
        String token = request.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer ")) {
            throw new AuthenticationException("令牌为空或格式错误");
        }
        String realToken = token.substring(7);
        Claims claims = JwtUtil.parseJwt(jwtProperties.getSecretKey(), realToken);

        //查询redis
        String cacheKey = "userToken::" + claims.get("id");
        String usernameGetFromRedis = stringRedisTemplate.opsForValue().get(cacheKey);
        if(usernameGetFromRedis != null){
            log.debug("解析token缓存命中");
            if(!usernameGetFromRedis.equals(claims.get("username"))){
                throw new AuthenticationException("token异常");
            }
            return true;
        }

        //缓存未命中，查询数据库
        log.debug("解析token，缓存未命中，查询数据库");
        boolean isTokenTrue = userMapper.verifyUser(claims.get("id"), claims.get("username"));
        if (!isTokenTrue){
            throw new AuthenticationException("token异常");
        }

        return true;
    }
}