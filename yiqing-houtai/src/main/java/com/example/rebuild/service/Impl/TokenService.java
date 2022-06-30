package com.example.rebuild.service.Impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.rebuild.entity.Admin;
import com.example.rebuild.entity.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    //验证身份信息
    public String getToken(User user) {

        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60* 60 * 500;
        Date end = new Date(currentTime);
        String token = "";

        token = JWT.create().withAudience(user.getId().toString()).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }

    public String getToken(Admin admin) {

        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60* 60 * 500;
        Date end = new Date(currentTime);
        String token = "";

        token = JWT.create().withAudience(admin.getId().toString()).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(admin.getPassword()));
        return token;
    }

}