package com.comoco.demoshop.controller;

import com.comoco.demoshop.dto.data.CreateUserData;
import com.comoco.demoshop.dto.request.LoginRequest;
import com.comoco.demoshop.dto.request.RegisterRequest;
import com.comoco.demoshop.dto.response.LoginResponse;
import com.comoco.demoshop.exception.BadRequestException;
import com.comoco.demoshop.exception.UnauthorizedException;
import com.comoco.demoshop.model.User;
import com.comoco.demoshop.service.JwtService;
import com.comoco.demoshop.dto.data.TokenUserDetail;
import com.comoco.demoshop.service.UserService;
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
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class AuthController {
    @Autowired
    JwtService jwtService;

    @Autowired
    UserService userService;

    @ApiOperation("登入")
    @ApiResponses({
        @ApiResponse(code = 200, message = "登入成功"),
        @ApiResponse(code = 401, message = "登入失敗")
    })
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value="/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) throws Exception {
        Optional<User> userOpt = this.userService.validatedUserPassword(request.getUsername(), request.getPassword());
        if (userOpt.isEmpty()) {
            throw new UnauthorizedException("Username or Password not validated");
        }

        User user = userOpt.get();
        TokenUserDetail tokenUserDetails = TokenUserDetail.builder()
            .name(user.getName())
            .accountId(user.getId())
            .role(user.getRole())
            .build();

        return LoginResponse.builder()
            .token(jwtService.generateToken(tokenUserDetails))
            .build();
    }

    @ApiOperation("註冊")
    @ApiResponses({
        @ApiResponse(code = 200, message = "登入成功"),
        @ApiResponse(code = 401, message = "登入失敗")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping(value="/register")
    public void register(@Valid @RequestBody RegisterRequest request) throws Exception {
        Optional<User> user = this.userService.findByUsername(request.getUsername());
        if (!user.isEmpty()) {
            throw new BadRequestException("Account exists already");
        }

        this.userService.createUser(
            CreateUserData.builder()
                .email(request.getEmail())
                .name(request.getName())
                .username(request.getUsername())
                .password(request.getPassword())
                .build()
        );
    }
}
