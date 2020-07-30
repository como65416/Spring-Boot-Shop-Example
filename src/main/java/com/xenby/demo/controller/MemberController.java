package com.xenby.demo.controller;

import com.xenby.demo.exception.UnauthorizedException;
import com.xenby.demo.form.LoginForm;
import com.xenby.demo.service.JwtService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MemberController {
    @Autowired
    JwtService jwtService;

    @ApiOperation("登入")
    @ApiResponses({
            @ApiResponse(code = 200, message = "登入成功"),
            @ApiResponse(code = 401, message = "登入失敗")
    })
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value="/login")
    public Map<String, Object> login(@Valid @RequestBody LoginForm loginForm) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        if (!loginForm.getUsername().equals("user") || !loginForm.getPassword().equals("1234")) {
            throw new UnauthorizedException("Username or Password not validated");
        }

        Map<String, Object> data = new HashMap<String, Object>();
        map.put("token", jwtService.generateToken(data));

        return map;
    }
}
