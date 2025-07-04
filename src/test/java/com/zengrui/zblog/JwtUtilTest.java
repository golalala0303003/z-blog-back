package com.zengrui.zblog;

import com.zengrui.zblog.common.properties.JwtProperties;
import com.zengrui.zblog.common.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class JwtUtilTest {
    @Autowired
    private JwtProperties jwtProperties;

    @Test
    void jwtTest() {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("username", "zengrui");
        claims.put("id","1");
        String userJwtToken = JwtUtil.createJwt(jwtProperties.getSecretKey(), jwtProperties.getExpirationTime(), claims);
        System.out.println("用户的jwt令牌为" + userJwtToken);
        Claims claims1 = JwtUtil.parseJwt(jwtProperties.getSecretKey(), userJwtToken);
        System.out.println(claims1);
    }
}
