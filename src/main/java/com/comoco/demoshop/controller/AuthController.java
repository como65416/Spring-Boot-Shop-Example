package com.comoco.demoshop.controller;

import com.comoco.demoshop.dto.request.LoginRequest;
import com.comoco.demoshop.dto.response.LoginResponse;
import com.comoco.demoshop.exception.UnauthorizedException;
import com.comoco.demoshop.model.User;
import com.comoco.demoshop.repository.AccountRepository;
import com.comoco.demoshop.service.JwtService;
import com.comoco.demoshop.dto.data.TokenUserDetail;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {
    @Autowired
    JwtService jwtService;

    @Autowired
    AccountRepository accountRepository;

    @ApiOperation("登入")
    @ApiResponses({
            @ApiResponse(code = 200, message = "登入成功"),
            @ApiResponse(code = 401, message = "登入失敗")
    })
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value="/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User account = accountRepository.findByUsername(request.getUsername());
        if (account == null || !bCryptPasswordEncoder.matches(request.getPassword(), account.getPassword())) {
            throw new UnauthorizedException("Username or Password not validated");
        }

        TokenUserDetail tokenUserDetails = TokenUserDetail.builder()
                .name(account.getName())
                .accountId(account.getId())
                .role(account.getRole())
                .build();

        LoginResponse response = new LoginResponse();
        response.setToken(jwtService.generateToken(tokenUserDetails));

        return response;
    }
}
