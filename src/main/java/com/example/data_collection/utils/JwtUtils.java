package com.example.data_collection.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {

    //盐值（密匙）
    private static String key = "ad128433d8e3356e7024009bf6add2ab";

    private static long ttl = 4*60*60*1000; //2个小时

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

    /**
     * 重载方法，设置时间为2小时
     * @param claims 载荷内容
     * @param ttl    有效时长
     * @return 创建token
     */
    public static String createToken(Map<String, Object> claims, long ttl) {
        com.example.data_collection.utils.JwtUtils.ttl = ttl;
        return createToken(claims);
    }
    // 刷新token
    public static String createRefreshToken(String userId, long ttl) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder().setId(userId)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, key);
        if (ttl > 0) {
            builder.setExpiration(new Date(nowMillis + ttl));
        }
        return builder.compact();
    }

    /**
     * 生成token
     * @param claims 载荷
     * @return token
     */
    public static String createToken(Map<String, Object> claims) {

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder()
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, key);

        if (claims != null) {
            builder.setClaims(claims);
        }

        if (ttl > 0) {
            builder.setExpiration(new Date(nowMillis + ttl));
        }
        return builder.compact();
    }

    // 解析token
    public static Claims parseJWT(String jwtStr) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwtStr)
                .getBody();
    }
}
