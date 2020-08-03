package com.xenby.demo.controller;

import com.xenby.demo.exception.UnauthorizedException;
import com.xenby.demo.form.LoginForm;
import com.xenby.demo.model.JwtClaimsData;
import com.xenby.demo.model.User;
import com.xenby.demo.repository.UserRepository;
import com.xenby.demo.service.JwtService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MemberController {
    @Autowired
    JwtService jwtService;

    @Autowired
    UserRepository userRepository;

    @ApiOperation("登入")
    @ApiResponses({
            @ApiResponse(code = 200, message = "登入成功"),
            @ApiResponse(code = 401, message = "登入失敗")
    })
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value="/login")
    public Map<String, Object> login(@Valid @RequestBody LoginForm loginForm) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = userRepository.findByUsername(loginForm.getUsername());
        if (user == null || !bCryptPasswordEncoder.matches(loginForm.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("Username or Password not validated");
        }

        JwtClaimsData jwtClaimsData = new JwtClaimsData();
        jwtClaimsData.setUsername(user.getUsername());
        jwtClaimsData.setRole(user.getRole());
        map.put("token", jwtService.generateToken(jwtClaimsData));

        return map;
    }
}
