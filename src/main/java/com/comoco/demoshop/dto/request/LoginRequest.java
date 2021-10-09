package com.comoco.demoshop.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
    @NotBlank(message = "Name is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;
}
