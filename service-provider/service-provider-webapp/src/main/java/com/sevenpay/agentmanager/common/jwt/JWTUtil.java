package com.sevenpay.agentmanager.common.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class JWTUtil {


    /**
     * 过期时间 24 小时
     */
    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000L;
    //private static final long EXPIRE_TIME =  60 * 1000;    测试60秒
    /**
     * 校验token是否正确
     * @param token 密钥
     * @param secret 用户的openId
     * @return 是否正确
     */
    public static boolean verify(String token, String userId, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("userId", userId)
                    .withClaim("secret", secret)
                    .build();
            //TODO 这里做什么？为什么直接返回true
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的用户名
     */
    public static String getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asString();
        } catch (JWTDecodeException e) {
            return e.getMessage();
        }
    }

    /**
     * 获取token中的 openId
     * @param token
     * @return
     */
    public static String getOpenId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);

            return jwt.getClaim("secret").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名,5min后过期
     * @param userId 用户名
     * @param secret 用户的密码
     * @return 加密的token
     */
    public static String sign(String userId, String secret) {
        try {
            Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带username信息
            return JWT.create()
                    .withClaim("userId", userId)
                    .withClaim("secret", secret)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }


    public static void main(String[] args) {
        /**
         * 测试生成一个token
         */
//        String sign = sign("GYZB-0007", "963852741");
//        System.out.println("测试生成一个token\n"+sign);


        String dd = getUserId("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzZWNyZXQiOiI4OTg5ODkiLCJleHAiOjE1NzM2NDEyODYsInVzZXJJZCI6IjdhMjUxODBlZWZlZTQyM2E5OTJkMjlkOTcxMmI2ZjlkIn0.i-fKA18cH8go_BfOWErfzMI1jNQ32KJgz_9e9W9fbiQ");
        System.out.println(dd+"-----------");
    }

}
