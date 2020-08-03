package com.xenby.demo.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xenby.demo.model.JwtClaimsData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    @Value("${jwt.key}")
    private String jwtKey;

    public String generateToken(JwtClaimsData jwtClaimsData) {
        Algorithm algorithm = Algorithm.HMAC256(this.jwtKey);
        JWTCreator.Builder builder = JWT.create();

        builder = builder.withClaim("username", jwtClaimsData.getUsername());
        builder = builder.withClaim("role", jwtClaimsData.getRole());

        builder = builder.withClaim("exp", (new Date()).getTime() + 3600);
        String token = builder.sign(algorithm);

        return token;
    }

    public JwtClaimsData validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(this.jwtKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);

        JwtClaimsData jwtClaimsData = new JwtClaimsData();
        jwtClaimsData.setUsername(jwt.getClaim("username").asString());
        jwtClaimsData.setRole(jwt.getClaim("role").asString());

        return jwtClaimsData;
    }
}
