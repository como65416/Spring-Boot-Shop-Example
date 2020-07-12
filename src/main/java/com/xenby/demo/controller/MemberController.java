package com.xenby.demo.controller;

import com.xenby.demo.service.JwtService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MemberController {
    @Autowired
    JwtService jwtService;

    @PostMapping(value="/login")
    @ApiOperation("登入")
    public ResponseEntity<Map<String, Object>> deleteStudent(@RequestBody Map<String, Object> body) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (!body.get("username").equals("user") || !body.get("password").equals("1234")) {
            map.put("message", "Username or Password not validated");
            return new ResponseEntity<Map<String,Object>>(map, HttpStatus.UNAUTHORIZED);
        }

        Map<String, Object> data = new HashMap<String, Object>();
        map.put("token", jwtService.generateToken(data));
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }
}
