package com.xenby.demo.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xenby.demo.dto.data.TokenUserDetail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.key}")
    private String jwtKey;

    public String generateToken(TokenUserDetail tokenUserDetai) {
        Algorithm algorithm = Algorithm.HMAC256(this.jwtKey);
        JWTCreator.Builder builder = JWT.create();

        builder = builder.withClaim("account_id", tokenUserDetai.getAccountId());
        builder = builder.withClaim("username", tokenUserDetai.getName());
        builder = builder.withClaim("role", tokenUserDetai.getRole());

        builder = builder.withClaim("exp", (new Date()).getTime() + 3600);
        String token = builder.sign(algorithm);

        return token;
    }

    public TokenUserDetail loadUserDetailByToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(this.jwtKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);

        return TokenUserDetail.builder()
                .accountId(jwt.getClaim("account_id").asLong())
                .name(jwt.getClaim("username").asString())
                .role(jwt.getClaim("role").asString())
                .build();
    }
}
