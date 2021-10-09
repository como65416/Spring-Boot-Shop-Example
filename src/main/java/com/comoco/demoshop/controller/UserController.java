package com.comoco.demoshop.controller;

import com.comoco.demoshop.dto.data.TokenUserDetail;
import com.comoco.demoshop.dto.request.UpdateProfileRequest;
import com.comoco.demoshop.dto.response.ProfileResponse;
import com.comoco.demoshop.exception.UnauthorizedException;
import com.comoco.demoshop.model.User;
import com.comoco.demoshop.repository.UserRepository;
import com.comoco.demoshop.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation("取得基本資料")
    @ApiResponses({
            @ApiResponse(code = 200, message = "取得成功"),
            @ApiResponse(code = 401, message = "權限錯誤")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value="/profile")
    public ProfileResponse getProfile() throws Exception {
        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        Object principal =  auth.getPrincipal();
        Long userId = ((TokenUserDetail) principal).getAccountId();

        User user = this.userService.findById(userId).get();
        ProfileResponse profileResp = ProfileResponse.builder()
                .email(user.getEmail())
                .name(user.getName())
                .build();

        return profileResp;
    }


    @ApiOperation("取得基本資料")
    @ApiResponses({
            @ApiResponse(code = 200, message = "取得成功"),
            @ApiResponse(code = 401, message = "權限錯誤")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value="/profile")
    public void updateProfile(@Valid @RequestBody UpdateProfileRequest request) throws Exception {
        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        Object principal =  auth.getPrincipal();
        Long userId = ((TokenUserDetail) principal).getAccountId();

        this.userService.UpdateProfile(userId, request.getName(), request.getEmail());
    }
}
