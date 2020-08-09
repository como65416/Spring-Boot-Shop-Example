package com.xenby.demo.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xenby.demo.model.TokenUserDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.key}")
    private String jwtKey;

    public String generateToken(TokenUserDetails tokenUserDetails) {
        Algorithm algorithm = Algorithm.HMAC256(this.jwtKey);
        JWTCreator.Builder builder = JWT.create();

        builder = builder.withClaim("username", tokenUserDetails.getUsername());
        builder = builder.withClaim("role", tokenUserDetails.getRole());
        builder = builder.withClaim("companyId", tokenUserDetails.getCompanyId());

        builder = builder.withClaim("exp", (new Date()).getTime() + 3600);
        String token = builder.sign(algorithm);

        return token;
    }

    public TokenUserDetails loadUserDetailByToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(this.jwtKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);

        TokenUserDetails tokenUserDetails = new TokenUserDetails();
        tokenUserDetails.setUsername(jwt.getClaim("username").asString());
        tokenUserDetails.setRole(jwt.getClaim("role").asString());
        tokenUserDetails.setCompanyId(jwt.getClaim("companyId").asInt());

        return tokenUserDetails;
    }
}
