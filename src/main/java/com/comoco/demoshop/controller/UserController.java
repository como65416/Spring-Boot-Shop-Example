package com.comoco.demoshop.controller;

import com.comoco.demoshop.dto.data.TokenUserDetail;
import com.comoco.demoshop.dto.response.ProfileResponse;
import com.comoco.demoshop.exception.UnauthorizedException;
import com.comoco.demoshop.model.User;
import com.comoco.demoshop.repository.UserRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @ApiOperation("取得基本資料")
    @ApiResponses({
            @ApiResponse(code = 200, message = "取得成功"),
            @ApiResponse(code = 401, message = "權限錯誤")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value="/profile")
    public ProfileResponse login() throws Exception {
        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        Object principal =  auth.getPrincipal();
        Long userId = 0L;
        if (principal instanceof TokenUserDetail) {
            userId = ((TokenUserDetail) principal).getAccountId();
        }

        Optional<User> userOpt = this.userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new UnauthorizedException("user not found");
        }

        User user = userOpt.get();
        ProfileResponse profileResp = ProfileResponse.builder()
                .email(user.getEmail())
                .name(user.getName())
                .build();

        return profileResp;
    }
}
