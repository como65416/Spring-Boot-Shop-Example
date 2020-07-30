package com.xenby.demo.controller;

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
    @PostMapping(value="/login")
    public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody LoginForm loginForm) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (!loginForm.getUsername().equals("user") || !loginForm.getPassword().equals("1234")) {
            map.put("message", "Username or Password not validated");
            return new ResponseEntity<Map<String,Object>>(map, HttpStatus.UNAUTHORIZED);
        }

        Map<String, Object> data = new HashMap<String, Object>();
        map.put("token", jwtService.generateToken(data));

        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }
}
