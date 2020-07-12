package com.xenby.demo.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    @Value("${jwt.key}")
    private String jwtKey;

    public String generateToken(Map<String, Object> data) {
        Algorithm algorithm = Algorithm.HMAC256(this.jwtKey);
        JWTCreator.Builder builder = JWT.create();

        for (String key : data.keySet()) {
            builder = builder.withClaim(key, (String) data.get(key));
        }

        builder = builder.withClaim("exp", (new Date()).getTime() + 3600);
        String token = builder.sign(algorithm);

        return token;
    }

    public Map<String, Object> validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(this.jwtKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);

        Map<String, Object> data = new HashMap<String, Object>();
        for (String key : jwt.getClaims().keySet()) {
            data.put(key, jwt.getClaims().get(key));
        }

        return data;
    }
}
