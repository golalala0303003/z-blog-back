package com.zengrui.zblog.common.utils;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
    public static String createJwt(String secretKey, long expirationTime, Map<String, Object> claims) {
        long expMills = System.currentTimeMillis() + expirationTime;
        Date exp = new Date(expMills);

        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        JwtBuilder builder = Jwts.builder()
                .signWith(key)
                .claims(claims)
                .expiration(exp);
        return builder.compact();
    }

    public static Claims parseJwt(String secretKey, String token) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        JwtParser jwtParser = Jwts.parser()
                .verifyWith(key)
                .build();

        Jws<Claims> jws = jwtParser.parseSignedClaims(token);
        return jws.getPayload();
    }
}
